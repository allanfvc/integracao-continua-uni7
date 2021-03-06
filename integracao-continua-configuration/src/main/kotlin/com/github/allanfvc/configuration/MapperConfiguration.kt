package com.github.allanfvc.configuration

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter


@Configuration
class MapperConfiguration {

    @Bean
    fun jacksonBuilder(): Jackson2ObjectMapperBuilder {
        val builder = Jackson2ObjectMapperBuilder()
        builder.modules(KotlinModule())
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);

        return builder
    }

    @Bean
    fun mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter {
        return MappingJackson2HttpMessageConverter().apply {
            this.objectMapper = ObjectMapper().apply {
                registerModule(KotlinModule())
            }
        }
    }
}