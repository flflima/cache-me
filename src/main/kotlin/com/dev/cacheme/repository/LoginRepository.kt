package com.dev.cacheme.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*
import kotlin.time.Duration.Companion.seconds

@Component
@Scope("prototype")
class LoginRepository(cacheManager: CacheManager) {

    private val logger: Logger = LoggerFactory.getLogger(LoginRepository::class.java)

    private val cache: Cache = cacheManager.getCache("token")!!

    private val mutex = Mutex()

    companion object {
        private const val salt = "abc"
    }

    @Cacheable("token", sync = true)
    fun getToken(): String {
        simulateSlowService()
        return UUID.randomUUID().toString()
    }

    suspend fun getToken2(): String {
        return mutex.withLock {
            val key = "key"
            val cacheByKey = cache.get(key, String::class.java)
            if (cache.get(key) != null && cacheByKey != null) {
                logger.info("-----> #Cache")
                cacheByKey
            } else {
                waitslow()
                logger.info("-----> #Generating token")
                val token = UUID.randomUUID().toString()
                cache.put(key, token)
                token
            }
        }
    }

    suspend fun getToken3(id: String): String {
        return mutex.withLock {
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
            val key = Objects.hash(id + salt)
            val cacheByKey = cache.get(key, String::class.java)
            if (cache.get(key) != null && cacheByKey != null) {
                logger.info("-----> #Cache")
                cacheByKey
            } else {
                waitslow()
                logger.info("-----> #Generating token")
                val token = "${id}---ABC---${LocalDateTime.now()}"
                val valueWrapper = cache.putIfAbsent(key, token)
                valueWrapper?.get() as String? ?: token
            }
        }
    }

    private fun simulateSlowService() {
        try {
            logger.info("-----> #Generating token")
            val time = 3000L
            Thread.sleep(time)
        } catch (e: InterruptedException) {
            throw IllegalStateException(e)
        }
    }

    suspend fun waitslow() {
        logger.info("-----> #Slow Service coroutine")
        delay(3.seconds)
    }
}