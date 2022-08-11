package com.tyy;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String key = RoutingDataSourceContext.getDataSourceRoutingKey();
        return key;
    }

    @Override
    protected DataSource determineTargetDataSource() {
        DruidDataSource ds = (DruidDataSource)super.determineTargetDataSource();
        System.out.println(ds.getRawJdbcUrl());
        return ds;
    }
}
