package com.yizhi.service.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yizhi.medical.user.mapper")
public class UserConfig {
}
