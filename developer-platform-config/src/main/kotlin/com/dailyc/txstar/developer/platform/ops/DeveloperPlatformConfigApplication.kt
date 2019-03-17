package com.dailyc.txstar.developer.platform.ops

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableConfigServer
@EnableWebSecurity
class DeveloperPlatformConfigApplication

fun main(args: Array<String>) {
    runApplication<DeveloperPlatformConfigApplication>(*args)
}
