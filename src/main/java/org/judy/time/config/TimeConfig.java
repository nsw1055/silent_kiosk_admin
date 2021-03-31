package org.judy.time.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j;

@Configuration
@Log4j
@MapperScan(basePackages = "org.judy.time.mapper")
@ComponentScan(basePackages = "org.judy.time.service")
public class TimeConfig {

}
