package com.tyy;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @DbSetting(value = "master")
    public void showMasterTime() {
        var map = jdbcTemplate.queryForMap("SELECT NOW() as now");
        System.out.println(map);
    }

    @DbSetting(value = "slave")
    public void showSlaveTime() {
        var map = jdbcTemplate.queryForMap("SELECT NOW() as now");
        System.out.println(map);
    }

    public void showDefaultTime() {
        var map = jdbcTemplate.queryForMap("SELECT NOW() as now");
        System.out.println(map);
    }

    public void showExtTime() {
        var map = jdbcTemplate.queryForMap("SELECT NOW() as now");
        System.out.println(map);
    }
}
