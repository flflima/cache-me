package com.dev.cacheme.controller

import com.dev.cacheme.repository.LoginRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenController: GenericController() {

    @Autowired
    lateinit var loginRepository: LoginRepository

    @GetMapping("/token")
    fun getToken(): ResponseEntity<*> {
        return execute() {
            ResponseEntity.ok(Response(loginRepository.getToken()))
        }
    }
}

data class Response(val token: String)