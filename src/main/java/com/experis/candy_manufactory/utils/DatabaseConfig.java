package com.experis.candy_manufactory.utils;

import javax.sql.DataSource;
import com.zaxxer.hikari.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*This class is going to be run on its own when the app starts to config*/

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);
    }
}
