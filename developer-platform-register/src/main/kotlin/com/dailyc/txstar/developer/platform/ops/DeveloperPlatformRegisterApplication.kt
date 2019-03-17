package com.dailyc.txstar.developer.platform.ops

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class DeveloperPlatformRegisterApplication

fun main(args: Array<String>) {
    runApplication<DeveloperPlatformRegisterApplication>(*args)
}
