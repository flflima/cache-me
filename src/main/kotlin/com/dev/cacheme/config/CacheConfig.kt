package com.dev.cacheme.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
class CacheConfig {
//    @Bean
//    fun caffeineConfig(): Caffeine<*, *> {
//        return Caffeine.newBuilder()
//    }
//
//    @Bean
//    fun cacheManager(caffeine: Caffeine<*, *>): CacheManager {
//        val caffeineCacheManager = CaffeineCacheManager()
//        caffeineCacheManager.setCaffeine(caffeine as Caffeine<Any, Any>)
//        return caffeineCacheManager
//    }
}