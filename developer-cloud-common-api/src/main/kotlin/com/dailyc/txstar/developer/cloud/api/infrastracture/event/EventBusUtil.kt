package com.dailyc.txstar.developer.cloud.api.infrastracture.event

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.stereotype.Component
import org.springframework.web.context.ContextLoader


/**
 * 消息总线控制类
 */
@Component
class EventBusUtil : ApplicationEventPublisherAware {

    override fun setApplicationEventPublisher(applicationEventPublisher1: ApplicationEventPublisher) {
        applicationEventPublisher = applicationEventPublisher1
    }

    companion object {
        private lateinit var applicationEventPublisher: ApplicationEventPublisher

        /**
         * 发布开发者平台事件
         */
        fun <T : ApplicationEvent> post(event: T) {
            (ContextLoader.getCurrentWebApplicationContext() ?: applicationEventPublisher).publishEvent(event)
        }
    }
}