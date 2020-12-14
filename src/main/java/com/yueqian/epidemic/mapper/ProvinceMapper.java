package com.yueqian.epidemic.mapper;

import com.yueqian.epidemic.bean.ProvinceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface ProvinceMapper {
    @Select("SELECT \n" +
            "  * \n" +
            "FROM\n" +
            "  provinces \n" +
            "WHERE province_id NOT IN \n" +
            "  (SELECT \n" +
            "    province_id \n" +
            "  FROM\n" +
            "    epidemics \n" +
            "  WHERE data_year = #{arg0} \n" +
            "    AND data_month = #{arg1}\n" +
            "    AND data_day = #{arg2}) ORDER BY province_id LIMIT 0,6;")
    List<ProvinceInfo> findNoDataProvinceList(int year, int month, int day);
}
