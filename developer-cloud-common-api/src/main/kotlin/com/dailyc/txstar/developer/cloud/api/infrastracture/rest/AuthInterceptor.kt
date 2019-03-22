package com.dailyc.txstar.developer.cloud.api.infrastracture.rest

import com.alibaba.fastjson.JSONObject
import com.jd.jr.cpdp.common.infrastracture.rest.context.InvestorContext
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.util.StreamUtils
import org.springframework.web.servlet.HandlerInterceptor
import java.nio.charset.Charset
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 权限认证拦截器
 * 当用户未登录时，直接拦截返回
 * @author Wang Weiwei / email : weiwei02@vip.qq.com
 * @since 2018/9/27
 * @version 1.0.0
 */
class AuthInterceptor : HandlerInterceptor {
    private val log = LoggerFactory.getLogger("ACCESS")
     override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
         // header 中带有lendId 属性，可认为已经登录
        if(StringUtils.isNotEmpty(request.getHeader("lendId"))){
            InvestorContext.lendId = request.getHeader("lendId")
            return true
            // 链接参数中带有 sessionId 属性，可认为是内部调用，也正常放行
        }else if(StringUtils.isNotEmpty(request.getParameter("sessionId"))){
            InvestorContext.lendId = request.getParameter("sessionId")
            return true
        }
         // 当都不放行时，记录日志
         var param = JSONObject.toJSONString(request.parameterMap)
         if (StringUtils.isEmpty(param)){
             param = StreamUtils.copyToString(request.inputStream, Charset.forName("UTF-8"))
         }
         log.info("visit no login | url = ${request.requestURL} | param = ", param)
         response.characterEncoding = "UTF-8"
         response.contentType = "application/json; charset=utf-8"
         val result = RestResult(ResponseCode.REST_USER_LOGIN_ERROR, "")
         response.writer.append(result.toJSONString()).flush()
         return false
    }
}