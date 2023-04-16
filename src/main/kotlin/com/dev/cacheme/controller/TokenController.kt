package com.dev.cacheme.controller

import com.dev.cacheme.repository.LoginRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenController {

    @Autowired
    lateinit var loginRepository: LoginRepository

    @GetMapping("/token")
    fun getToken() = loginRepository.getToken()
}