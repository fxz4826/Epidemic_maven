package com.yueqian.epidemic.service.impl;

import com.yueqian.epidemic.bean.DailyEpidemicsInfo;
import com.yueqian.epidemic.bean.EpidemicDetailInfo;
import com.yueqian.epidemic.bean.EpidemicInfo;
import com.yueqian.epidemic.bean.ProvinceInfo;
import com.yueqian.epidemic.mapper.EpidemicMapper;
import com.yueqian.epidemic.mapper.ProvinceMapper;
import com.yueqian.epidemic.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class EpidemicServiceImpl implements EpidemicService {

    @Autowired
    private EpidemicMapper epidemicMapper;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public List<ProvinceInfo> saveEpidemicinfos(Integer userId, DailyEpidemicsInfo dailyEpidemicsInfo) {
        String date = dailyEpidemicsInfo.getDate();
        List<EpidemicInfo> array= dailyEpidemicsInfo.getArray();

        String[] strings = date.split("-");
        int year = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);
        int day = Integer.parseInt(strings[2]);
        for (int i=0;i<array.size();i++){
            //epidemicInfo封装了页面提交过来的数据（province_id）
            EpidemicInfo epidemicInfo = array.get(i);
            epidemicInfo.setDataYear(year);
            epidemicInfo.setDataMonth(month);
            epidemicInfo.setDataDay(day);
            epidemicInfo.setUserId(userId);
            epidemicInfo.setInputDate(new Date()); //设置当前的时间作为数据录入的时间
            //保存了所以的疫情信息数据
            epidemicMapper.saveEpidemicInfos(epidemicInfo);
        }
        //返回下一组没有录入的疫情省份列表
        return provinceMapper.findNoDataProvinceList(year,month,day);
    }

    @Override
    public List<EpidemicDetailInfo> findEpidemicInfoTotal(){
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);

       List<EpidemicDetailInfo> epidemicDetailInfoTotal= epidemicMapper.findEpidemicInfoTotal(year,month,day);
       return epidemicDetailInfoTotal;
    }
}
