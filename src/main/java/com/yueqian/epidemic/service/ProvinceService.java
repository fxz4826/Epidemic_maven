package com.yueqian.epidemic.service;

import com.yueqian.epidemic.bean.ProvinceInfo;

import java.util.List;

public interface ProvinceService {

    List<ProvinceInfo> noDataProvinceList(String date);
}
