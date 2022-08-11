package com.tyy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DynamicDatasourceApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void testMultiDataSource() {
        System.out.println("showMasterTime：");
        userService.showMasterTime();

        System.out.println("showSlaveTime：");
        userService.showSlaveTime();

        System.out.println("showDefaultTime：");
        userService.showDefaultTime();

        //使用方法内手动切换数据源
        System.out.println("showExtTime：");
        RoutingDataSourceContext.setDataSourceRoutingKey("slave");
        userService.showExtTime();
    }

}
