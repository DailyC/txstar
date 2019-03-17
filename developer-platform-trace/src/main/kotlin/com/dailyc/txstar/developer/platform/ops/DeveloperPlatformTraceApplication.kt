package com.dailyc.txstar.developer.platform.ops

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import zipkin.server.internal.EnableZipkinServer

@SpringBootApplication
@EnableZipkinServer
@EnableEurekaClient
class DeveloperPlatformTraceApplication

fun main(args: Array<String>) {
    runApplication<DeveloperPlatformTraceApplication>(*args)
}

