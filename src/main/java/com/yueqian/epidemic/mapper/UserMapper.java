package com.yueqian.epidemic.mapper;

import com.yueqian.epidemic.bean.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE account=#{account} AND PASSWORD=#{password}")
    UserInfo login(UserInfo userInfo);

    @Insert("INSERT INTO users(account,password,user_name) VALUES(#{account},#{password},#{userName})")
    void regist(UserInfo userInfo);
}
