package com.dailyc.txstar.developer.cloud.api.infrastracture.exception

import com.dailyc.txstar.developer.cloud.api.infrastracture.rest.RestResult
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest


/**
 * 开发者平台异常处理器
 * 当控制器发生异常时，在此处统一捕获，不做单独处理
 * @author Wang Weiwei / email : weiwei02@vip.qq.com
 * @since 2018/9/27
 * @version 1.0.0
 */
@ControllerAdvice
class CpdpRunExceptionResolver{
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun resolveException(request: HttpServletRequest, e:Exception ) : RestResult<Nothing> {

        return if (e is CpdpRunException) {
            log.error("resolveException CpdpRunException| uri = ${request.requestURL} | error = {}", e.toJsonString())
            RestResult(e.code, e.msg)

        }else {
            log.error("resolveException| uri = ${request.requestURL} | error = ", e)
            RestResult.failureResult(null)
        }
    }
}
