package com.yueqian.epidemic.service.impl;

import com.yueqian.epidemic.bean.ProvinceInfo;
import com.yueqian.epidemic.mapper.ProvinceMapper;
import com.yueqian.epidemic.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public List<ProvinceInfo> noDataProvinceList(String date) {
        String [] strings=date.split("-");
        int year = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);
        int day = Integer.parseInt(strings[2]);
        List<ProvinceInfo> noDataProvinceList=provinceMapper.findNoDataProvinceList(year,month,day);
        System.out.println(noDataProvinceList);
        return noDataProvinceList;
    }
}
