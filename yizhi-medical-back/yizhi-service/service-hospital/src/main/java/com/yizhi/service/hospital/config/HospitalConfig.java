package com.yizhi.service.hospital.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yizhi.service.hospital.mapper")
public class HospitalConfig {
}
