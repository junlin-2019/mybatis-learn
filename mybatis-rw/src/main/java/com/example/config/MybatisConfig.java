package com.example.config;

import com.example.plugins.DynamicPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @Description:
 * @Author: admin
 * @Date: 2020/9/28 17:14
 */
@Configuration
@MapperScan("com.example.dao")
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {

    private static String mybatisConfigPath = "mybatis-config.xml";

    @Autowired
    @Qualifier("multipleDataSource")
    private DataSource multipleDataSource;

    @Bean("sqlSessionFactoryBean")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(multipleDataSource);
        bean.setTypeAliasesPackage("com.example.entity");
        bean.setConfigLocation(new ClassPathResource(mybatisConfigPath));
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
        return bean.getObject();

    }

    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DynamicDataSourceTransactionManager(multipleDataSource);
    }
}
