package com.dailyc.txstar.developer.cloud.api.infrastracture.rest

import com.alibaba.fastjson.JSONObject
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate


/**
 * 远程服务执行器
 * @author Wang Weiwei / email : weiwei02@vip.qq.com
 * @since 2018/9/27
 * @version 2.0.0
 */
@Component
class RestInvoker (
        private val restTemplate: RestTemplate
){
    private val log = LoggerFactory.getLogger(javaClass)
    /**
     * 发起请求，并获取远程服务返回值
     *
     * 内部远程调用使用 post 方法
     * @param serverName 服务名
     * @param interfaceName 接口名
     * @param param 参数
     * @param sessionId 资方编号，隐式的在链接后传入
     * @return 返回值中会拆除掉公共报文，如果请求成功，则返回报文体中的json对象
     * 如果请求失败，则返回 null
     * */
    fun post(interfaceName:String, param:Any ? = null ,serverName:String = "", sessionId:String? = null): String? {
        val  restResult = postForRest(interfaceName, param, serverName, sessionId) ?: return null
        return if (restResult.isSuccess()){
            restResult.data
        }else null
    }

    /**
     * 将普通参数转换成复合MAP形式
     * */
    fun convertParam2MuiltiMap(param: Any?): LinkedMultiValueMap<String, String> {
        val paramMap = LinkedMultiValueMap<String, String>()
        if (param != null) {
            val paramJson = JSONObject.toJSON(param)
            if (paramJson is JSONObject) {
                val keys = paramJson.keys
                for (key in keys) {
                    paramMap[key] = arrayListOf(paramJson.getString(key))
                }
            }
        }
        return paramMap
    }


    /**
     * 远程发送post请求，并将获取的内容转换成 RestResult 对象
     * 内部远程调用使用 post 方法
     * @param serverName 服务名
     * @param interfaceName 接口名
     * @param param 参数
     * @param sessionId 资方编号，隐式的在链接后传入
     * @return 返回值中会拆除掉公共报文，如果请求成功，则返回报文体中的json对象
     * 如果请求失败，则返回 null
     * */
    fun postForRest(interfaceName:String, param:Any ? = null ,serverName:String = "", sessionId:String? = null):
            RestResult<String>? {
        val wrapperInterfaceName = if (sessionId == null) interfaceName else "$interfaceName?sessionId=$sessionId"
        val url = if (StringUtils.isEmpty(serverName)) wrapperInterfaceName else serverName + wrapperInterfaceName
        val desc = "post| url = $url | param = {}"
        var paramMap = LinkedMultiValueMap<String, String>()
        try {
            paramMap = convertParam2MuiltiMap(param)
            val responseStr = restTemplate.postForEntity<String>(url, paramMap, String::class.java).body
            val restResult = RestResult.fromJSONString(responseStr!!)
            log.debug(desc, "${JSONObject.toJSONString(paramMap)} | response = $responseStr")
            return restResult
        } catch (e: Exception) {
            log.error(desc, "${JSONObject.toJSONString(paramMap)} | error = ", e)
        }
        return null
    }
}