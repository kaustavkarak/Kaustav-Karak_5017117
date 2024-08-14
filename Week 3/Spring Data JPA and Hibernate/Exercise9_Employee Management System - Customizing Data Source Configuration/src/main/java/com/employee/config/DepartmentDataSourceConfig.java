package com.employee.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class DepartmentDataSourceConfig {

    @Bean
    public DataSource departmentDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:departmentdb")
                .username("sa")
                .password("")
                .driverClassName("org.h2.Driver")
                .build();
    }
}
