package com.yueqian.epidemic;
//mybatis的配置类

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.yueqian.epidemic.mapper")
public class MybatisConfig {
@Bean
    public BasicDataSource basicDataSource(){
        BasicDataSource basicDataSource=new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/epidemic");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("1234");

        //配置连接池的相关参数
        basicDataSource.setInitialSize(3);//连接池的初始容量
        basicDataSource.setMaxActive(10);//连接最大活动数量
        basicDataSource.setMaxIdle(1);//连接最大的空闲数量
        basicDataSource.setMaxWait(4000);//连接的超时时间
        basicDataSource.setDefaultAutoCommit(false);//取消sql语句的自动提交
        return basicDataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        //给实体类起别名
        factoryBean.setTypeAliasesPackage("com.yueqian.epidemic.bean");

        //创建一个配置对象，该对象
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);

        SqlSessionFactory sessionFactory = null;
        try {
            sessionFactory = factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
