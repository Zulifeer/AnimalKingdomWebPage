package com.Capston2.joel_y_seba.AnimalKingdomWebPage.Config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class Config {

    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/AnimalKingdom");
        dataSourceBuilder.username("AK");
        dataSourceBuilder.password("123456789*");
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        return dataSourceBuilder.build();
    }

    @Bean
    public ViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/JSP/");
        bean.setSuffix(".jsp");
        return bean;
    }
}