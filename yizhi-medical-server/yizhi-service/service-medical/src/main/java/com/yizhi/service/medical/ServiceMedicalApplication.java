package com.yizhi.service.medical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yizhi")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.yizhi")
public class ServiceMedicalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMedicalApplication.class,args);
    }
}
