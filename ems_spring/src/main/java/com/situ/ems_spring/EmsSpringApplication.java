package com.situ.ems_spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.situ.ems_spring.mapper")
public class EmsSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsSpringApplication.class, args);
    }
    // quartz定时任务
    @EnableScheduling
    public class AdminApplication {}
}
