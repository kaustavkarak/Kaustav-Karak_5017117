package com.employee.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class EmployeeDataSourceConfig {

    @Bean
    public DataSource employeeDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:employeedb")
                .username("sa")
                .password("")
                .driverClassName("org.h2.Driver")
                .build();
    }
}
