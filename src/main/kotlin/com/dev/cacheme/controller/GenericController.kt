package com.dev.cacheme.controller

import org.springframework.http.ResponseEntity

abstract class GenericController {

    fun execute(fn: () -> ResponseEntity<Any>): ResponseEntity<Any> {
        println("antes")
        val result = fn.invoke()
        println("depois")
        return result
    }
}