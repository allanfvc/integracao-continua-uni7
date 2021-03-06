package com.github.allanfvc.springweb.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class HomeController {

    @Value("\${spring.application.version}")
    lateinit var version: String

    @Value("\${spring.application.name}")
    lateinit var name: String

    @GetMapping
    fun helloWorld(): String {
        return "Hello world :: $name :: ($version)"
    }
}