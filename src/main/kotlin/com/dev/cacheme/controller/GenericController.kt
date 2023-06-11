package com.dev.cacheme.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

abstract class GenericController {
    private val logger: Logger = LoggerFactory.getLogger(GenericController::class.java)

    fun execute(fn: () -> ResponseEntity<Any>): ResponseEntity<Any> {
        logger.debug("antes")
        val result = fn.invoke()
        logger.debug("depois")
        return result
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleException(exception: RuntimeException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(exception.message!!))
    }
}

data class ErrorResponse(val message: String)