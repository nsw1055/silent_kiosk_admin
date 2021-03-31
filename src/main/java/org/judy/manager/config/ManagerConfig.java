package org.judy.manager.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = "org.judy.manager.mapper")
@ComponentScan(basePackages = "org.judy.manager.service")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class ManagerConfig {

}
