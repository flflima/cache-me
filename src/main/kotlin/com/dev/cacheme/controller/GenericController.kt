package com.dev.cacheme.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity

abstract class GenericController {
    private val logger: Logger = LoggerFactory.getLogger(GenericController::class.java)

    fun execute(fn: () -> ResponseEntity<Any>): ResponseEntity<Any> {
        logger.debug("antes")
        val result = fn.invoke()
        logger.debug("depois")
        return result
    }
}