package org.judy.store.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = "org.judy.store.mapper")
@ComponentScan(basePackages = "org.judy.store.service")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class StoreConfig {

}
