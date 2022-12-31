package com.dev.cacheme.cacheme.repository

import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
@Scope("prototype")
class LoginRepository {

    @Cacheable("token")
    fun getToken(): String {
        simulateSlowService()
        return UUID.randomUUID().toString()
    }

    private fun simulateSlowService() {
        try {
            val time = 3000L
            Thread.sleep(time)
            println("${LocalDateTime.now()} #Generating -----------")
        } catch (e: InterruptedException) {
            throw IllegalStateException(e)
        }
    }
}