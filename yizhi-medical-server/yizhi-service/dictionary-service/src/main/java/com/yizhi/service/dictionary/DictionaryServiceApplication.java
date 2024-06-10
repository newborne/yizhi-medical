package com.yizhi.service.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.yizhi")
@EnableDiscoveryClient
public class DictionaryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DictionaryServiceApplication.class, args);
    }
}
