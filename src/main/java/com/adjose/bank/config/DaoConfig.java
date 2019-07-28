package com.adjose.bank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.adjose.bank.dao.jpa")
@EnableRedisRepositories(basePackages = "com.adjose.bank.dao.redis")
public class DaoConfig {
}
