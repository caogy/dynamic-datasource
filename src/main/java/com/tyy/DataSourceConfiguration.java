package com.tyy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfiguration {

    @Bean("masterDataSourceProperties")
    @ConfigurationProperties("datasource.master")
    DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("slaveDataSourceProperties")
    @ConfigurationProperties("datasource.slave")
    DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    DataSource dataSource(@Autowired @Qualifier("masterDataSourceProperties") DataSourceProperties master,
                          @Autowired @Qualifier("slaveDataSourceProperties") DataSourceProperties slave) {

        DataSource slaveDataSource = slave.initializeDataSourceBuilder().build();
        DataSource masterDataSource = master.initializeDataSourceBuilder().build();

        RoutingDataSource dataSource = new RoutingDataSource();

        Map<Object, Object> map = new HashMap<>();
        map.put("master", masterDataSource);
        map.put("slave", slaveDataSource);

        dataSource.setTargetDataSources(map);
        dataSource.setDefaultTargetDataSource(masterDataSource);

        return dataSource;
    }

    @Bean
    @Primary
    JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    DataSourceTransactionManager dataSourceTransactionManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
