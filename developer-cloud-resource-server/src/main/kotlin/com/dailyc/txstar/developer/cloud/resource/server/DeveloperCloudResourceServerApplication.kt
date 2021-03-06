package com.dailyc.txstar.developer.cloud.resource.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class DeveloperCloudResourceServerApplication

fun main(args: Array<String>) {
    runApplication<DeveloperCloudResourceServerApplication>(*args)
}
