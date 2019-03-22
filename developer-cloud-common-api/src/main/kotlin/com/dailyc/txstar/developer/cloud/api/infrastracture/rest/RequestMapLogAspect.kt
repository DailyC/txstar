package com.dailyc.txstar.developer.cloud.api.infrastracture.rest

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.jd.jr.cpdp.common.infrastracture.rest.context.InvestorContext
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

/**
 * 请求日志记录切面
 * @author Wang Weiwei / email : weiwei02@vip.qq.com
 * @since 2018/9/28
 * @version 2.0.0
 */
@Aspect
@Component
class RequestMapLogAspect (
){
    private val log = LoggerFactory.getLogger("ACCESS")
    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)"
    )
    fun requestAnnotation(){}

    @Pointcut(value = "within(com.jd.jr..*Controller)")
    fun controller(){}

    @Pointcut(value = "requestAnnotation() && controller()")
    fun requestMappingPoint() {}


    /**
     * 正常返回时的日志
     * */
    @AfterReturning(value = "requestMappingPoint()", returning = "returnValue")
    fun controllerReturnLog(joinPoint: JoinPoint, returnValue:Any){
        val argString = argsString(joinPoint)
        val returnString = returnString(returnValue)
        val lendId = getContextLendId()
        log.info("interface = ${joinPoint.signature.toLongString()} | lendId = $lendId | $argString | return = $returnString")
    }

    private fun getContextLendId(): String {
        var lendId : String? = ""
        try {
            val attr = RequestContextHolder.getRequestAttributes()
            if (attr is ServletRequestAttributes){
                lendId = attr.request.session.getAttribute("lendId")?.toString()
            }
            if (null == lendId) {
                lendId = InvestorContext.lendId
            }
            if (org.springframework.util.StringUtils.isEmpty(lendId)){
                return "visit "
            }
        } catch (e: Exception) {
            return "visit"
        }
        return lendId
    }

    /**
     * 返回值字符串
     * */
    private fun returnString(returnValue: Any): String =
        try {
            JSONObject.toJSONString(returnValue)
        }catch (e:Exception){
            "$returnValue"
        }


    /**
     * 计算参数字符串
     * */
    private fun argsString(joinPoint: JoinPoint) : String{
        val args = joinPoint.args
        val argArray = JSONArray()
        argArray.addAll(args)
        return try {
            "args size = ${argArray.size}, args =  ${argArray.toJSONString()}"
        } catch (e: Exception) {
            val builder = StringBuilder()
            for (arg in args){
                builder.append("p = $arg  ,")
            }
            "args size = ${argArray.size}, args =  $builder"
        }

    }


    /**
     * 异常时的日志
     * */
    @AfterThrowing(value = "requestMappingPoint()", throwing = "e")
    fun controllerExceptionLog(joinPoint: JoinPoint, e:Exception){}
}