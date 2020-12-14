package com.yueqian.epidemic;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//Spring的配置类
@Configuration //表示当前类是配置类
//不让Spring管理Controller
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class))
@EnableTransactionManagement //默认的事务管理器
public class SpringConfig {
    @Bean //加在方法上，作用是把返回值纳入Spring容器里
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager dstm  = new DataSourceTransactionManager();
        dstm.setDataSource(dataSource);
        return dstm;
    }
}
