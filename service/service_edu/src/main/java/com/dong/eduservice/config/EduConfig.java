package com.dong.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.dong.eduservice.mapper")
public class EduConfig {

}
