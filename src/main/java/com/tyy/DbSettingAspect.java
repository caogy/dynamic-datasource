package com.tyy;

import lombok.var;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Aspect
public class DbSettingAspect {
    @Around("@annotation(dbSetting)")
    public Object dataSourceChange(ProceedingJoinPoint joinPoint, DbSetting dbSetting) throws Throwable {
        try (RoutingDataSourceContext dataSourceContext = new RoutingDataSourceContext(dbSetting.value())) {
            return joinPoint.proceed();
        }
    }

}

