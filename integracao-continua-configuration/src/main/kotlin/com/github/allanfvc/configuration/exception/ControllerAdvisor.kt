package com.github.allanfvc.configuration.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime
import java.util.*


@ControllerAdvice
class ControllerAdvisor : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    fun handleExceptionCustom(
        ex: Exception?, request: WebRequest?
    ): ResponseEntity<Any> {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["message"] = ex!!.localizedMessage
        return ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}