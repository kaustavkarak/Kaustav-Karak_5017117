package com.employee.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class EmployeeDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.employee")
    public DataSource employeeDataSource() {
        return DataSourceBuilder.create().build();
    }
}
