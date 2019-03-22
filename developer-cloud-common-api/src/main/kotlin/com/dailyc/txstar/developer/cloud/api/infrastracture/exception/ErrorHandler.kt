package com.dailyc.txstar.developer.cloud.api.infrastracture.exception

import com.dailyc.txstar.developer.cloud.api.infrastracture.rest.RestResult
import org.slf4j.LoggerFactory
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse


/**
 * 错误处理器
 * @author Wang Weiwei / email : weiwei02@vip.qq.com
 * @since 2018/10/8
 * @version 2.0.0
 */
//@RestController
class ErrorHandler : ErrorController {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun getErrorPath(): String {
        return "/error"
    }

    @RequestMapping("/error")
    fun error(response: HttpServletResponse, e:Exception):String {
        log.error("error handler ",e)
        return RestResult.failureResult(null).toJSONString()
    }
}