package com.qb32uu.spring.auto.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配
 */
@Configuration
@ConditionalOnResource(resources = "META-INF/spring.factories")
public class AutoConfiguration {


    /**
     * Student Bean
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(Student.class)
    public Student defaultStudent() {
        return Student.create();
    }

}
