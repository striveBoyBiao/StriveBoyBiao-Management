package com.zizhuling.cloudbase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.zizhuling.*.dao"})
@SpringBootApplication
public class CloudBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudBaseApplication.class, args);
	}
}
