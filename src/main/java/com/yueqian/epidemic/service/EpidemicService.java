package com.yueqian.epidemic.service;

import com.yueqian.epidemic.bean.DailyEpidemicsInfo;
import com.yueqian.epidemic.bean.EpidemicDetailInfo;
import com.yueqian.epidemic.bean.ProvinceInfo;

import java.util.List;

public interface EpidemicService {

   List<ProvinceInfo> saveEpidemicinfos(Integer userId, DailyEpidemicsInfo dailyEpidemicsInfo);

   public List<EpidemicDetailInfo> findEpidemicInfoTotal();

}
