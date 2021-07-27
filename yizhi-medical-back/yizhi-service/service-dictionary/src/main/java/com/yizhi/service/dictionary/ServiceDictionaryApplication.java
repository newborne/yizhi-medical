package com.yizhi.service.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages = "com.yizhi")
public class ServiceDictionaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDictionaryApplication.class,args);
    }
}