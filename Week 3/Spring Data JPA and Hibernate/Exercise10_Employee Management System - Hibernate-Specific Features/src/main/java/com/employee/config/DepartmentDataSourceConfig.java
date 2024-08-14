package com.employee.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DepartmentDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.department")
    public DataSource departmentDataSource() {
        return DataSourceBuilder.create().build();
    }
}
