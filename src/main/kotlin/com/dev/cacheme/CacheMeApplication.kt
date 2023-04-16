package com.dev.cacheme

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class CacheMeApplication

fun main(args: Array<String>) {
	runApplication<CacheMeApplication>(*args)
}
