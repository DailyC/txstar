package com.dailyc.txstar.developer.cloud.api.infrastracture.rest

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

/**
 * rest请求配置器
 * 该请求器仅使用于微服务内部使用
 * @author Wang Weiwei / email : weiwei02@vip.qq.com
 * @since 2018/9/27
 * @version 1.0.0
 */
@Configuration
class RestHttpConfig {
    @Bean
    @LoadBalanced
    fun restTemplate()= RestTemplate()
}