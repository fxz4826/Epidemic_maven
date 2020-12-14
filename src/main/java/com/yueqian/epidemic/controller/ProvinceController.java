package com.yueqian.epidemic.controller;

import com.yueqian.epidemic.bean.AjaxResponseInfo;
import com.yueqian.epidemic.bean.ProvinceInfo;
import com.yueqian.epidemic.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/province")

public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @RequestMapping("/ajax/noDataList")
    @ResponseBody  //将result的对象转换为json格式相应给页面
    public AjaxResponseInfo noDataProvinceList(String date) {
        System.out.println("ProvinceController" + date);
        AjaxResponseInfo ajaxResponseInfo = new AjaxResponseInfo();
        if (!StringUtils.isEmpty(date)) {
            //表示页面的日期有效
            //使用服务层对象调用服务层方法
          List<ProvinceInfo> provinceInfos= provinceService.noDataProvinceList(date);
          ajaxResponseInfo.setCode(0);
          ajaxResponseInfo.setMsg("请求成功");
          ajaxResponseInfo.setData(provinceInfos);
        } else {
            //表示页面没有提交日期
            ajaxResponseInfo.setCode(-1);
            ajaxResponseInfo.setMsg("请求参数有误");
    }
        return ajaxResponseInfo;
    }
}
