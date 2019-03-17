package com.dailyc.txstar.developer.platform.ops

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import zipkin.server.internal.EnableZipkinServer

@SpringBootApplication
@EnableZipkinServer
@EnableAdminServer
@EnableConfigServer
@EnableEurekaServer
@EnableWebSecurity
class DeveloperPlatformOpsApplication

fun main(args: Array<String>) {
    runApplication<DeveloperPlatformOpsApplication>(*args)
}
