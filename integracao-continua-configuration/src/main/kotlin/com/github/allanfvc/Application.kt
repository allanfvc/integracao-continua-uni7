package com.github.allanfvc

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableAutoConfiguration
@EnableCaching
@ComponentScan(basePackages = ["com.github.allanfvc.configuration"])
@EnableJpaRepositories(basePackages = ["com.github.allanfvc.springdata.repository"])
@EntityScan(basePackages = ["com.github.allanfvc.springdata.entity"])
class Application {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            runApplication<Application>(*args)
        }
    }
}

