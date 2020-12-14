package com.yueqian.epidemic.controller;


import com.yueqian.epidemic.bean.*;
import com.yueqian.epidemic.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/epidemicData")

public class EpidemicController {

    @Autowired
    private EpidemicService epidemicService;

    @RequestMapping("/ajax/input")
    @ResponseBody  //将return的对象转换成json格式响应给页面
    public AjaxResponseInfo saveEpidemicInfo(@RequestBody DailyEpidemicsInfo dailyEpidemicsInfo, HttpSession session){
        AjaxResponseInfo ajaxResponseInfo = new AjaxResponseInfo();
        UserInfo loginedUser=(UserInfo)session.getAttribute("loginedUser");
        System.out.println("saveEpidemicInfo:"+dailyEpidemicsInfo);
       // System.out.println("loginedUser:"+loginedUser.getUserId());
        if (loginedUser != null){
            //已经登陆了
           Integer userId = loginedUser.getUserId();
            List<ProvinceInfo> provinceInfos = epidemicService.saveEpidemicinfos(userId, dailyEpidemicsInfo);
           ajaxResponseInfo.setCode(0);
           ajaxResponseInfo.setMsg("保存成功");
           ajaxResponseInfo.setData(provinceInfos) ;
        }else{
            //表示用户没有登陆
            ajaxResponseInfo.setCode(-2);
            ajaxResponseInfo.setMsg("权限不足，请登陆后再试");

        }
        return ajaxResponseInfo;
    }

    @RequestMapping("/ajax/lastestData")
    @ResponseBody
    public AjaxResponseInfo findEpidemicInfoTotal(){
        List<EpidemicDetailInfo> epidemicInfoTotal = epidemicService.findEpidemicInfoTotal();
        AjaxResponseInfo ajaxResponseInfo= new AjaxResponseInfo();
        ajaxResponseInfo.setCode(0);
        ajaxResponseInfo.setMsg("查询成功");
        ajaxResponseInfo.setData(epidemicInfoTotal);
        return ajaxResponseInfo;
    }
}
