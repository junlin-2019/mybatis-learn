package com.example.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    
    @Value("${spring.datasource.db01.jdbcUrl}")
    private String db01Url;
    @Value("${spring.datasource.db01.username}")
    private String db01Username;
    @Value("${spring.datasource.db01.password}")
    private String db01Password;
    @Value("${spring.datasource.db01.driverClassName}")
    private String db01DiverClassName;

    @Bean("dataSource01")
    public DataSource dataSource01(){
        HikariDataSource dataSource01 = new HikariDataSource();
        dataSource01.setJdbcUrl(db01Url);
        dataSource01.setDriverClassName(db01DiverClassName);
        dataSource01.setUsername(db01Username);
        dataSource01.setPassword(db01Password);
        return dataSource01;
    }

    @Value("${spring.datasource.db02.jdbcUrl}")
    private String db02Url;
    @Value("${spring.datasource.db02.username}")
    private String db02Username;
    @Value("${spring.datasource.db02.password}")
    private String db02Password;
    @Value("${spring.datasource.db02.driverClassName}")
    private String db02DiverClassName;

    @Bean("dataSource02")
    public DataSource dataSource02(){
        HikariDataSource dataSource02 = new HikariDataSource();
        dataSource02.setJdbcUrl(db02Url);
        dataSource02.setDriverClassName(db02DiverClassName);
        dataSource02.setUsername(db02Username);
        dataSource02.setPassword(db02Password);
        return dataSource02;
    }
    @Bean("multipleDataSource")
    public DataSource multipleDataSource(@Qualifier("dataSource01") DataSource dataSource01,
                                         @Qualifier("dataSource02") DataSource dataSource02) {
        Map<Object, Object> datasources = new HashMap<Object, Object>();
        datasources.put("write", dataSource01);
        datasources.put("read", dataSource02);
        DynamicDataSource multipleDataSource = new DynamicDataSource();
        multipleDataSource.setDefaultTargetDataSource(dataSource01);
        multipleDataSource.setTargetDataSources(datasources);
        return multipleDataSource;
    }

}