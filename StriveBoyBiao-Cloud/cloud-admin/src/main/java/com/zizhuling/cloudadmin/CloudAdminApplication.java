package com.zizhuling.cloudadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients(basePackages = {"com.zizhuling"})
@EnableCaching
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.zizhuling.cloudadmin", "com.zizhuling.cloudcommon"})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class CloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAdminApplication.class, args);
    }

}
