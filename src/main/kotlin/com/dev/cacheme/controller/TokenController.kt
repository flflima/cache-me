package com.dev.cacheme.controller

import com.dev.cacheme.repository.LoginRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenController: GenericController() {

    @Autowired
    lateinit var loginRepository: LoginRepository

    val logger: Logger = LoggerFactory.getLogger(TokenController::class.java)

    @GetMapping("/token")
    fun getToken(): ResponseEntity<*> {
        return execute() {
            ResponseEntity.ok(Response(loginRepository.getToken()))
        }
    }

    @GetMapping("/token2")
    suspend fun getToken2(): ResponseEntity<*> {
        logger.info("-----> Coroutines")
        return ResponseEntity.ok(Response(loginRepository.getToken2()))
    }

    @GetMapping("/token3/{id}")
    suspend fun getToken3(@PathVariable id: String): ResponseEntity<*> {
        logger.info("-----> Coroutines")
        return ResponseEntity.ok(Response(loginRepository.getToken3(id)))
    }
}

data class Response(val token: String)