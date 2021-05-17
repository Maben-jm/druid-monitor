package com.maben.druid_monitor;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j(topic = "m.MainApplication")
@SpringBootApplication
@MapperScan(value = "com.maben.druid_monitor.mapper")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
        log.info("****************启动成功*****************");
    }
}
