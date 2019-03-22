package com.jd.jr.cpdp.common.infrastracture.rest.context

import org.apache.commons.lang3.StringUtils
import java.util.*

/** 资方上下文设置
 * @author wangweiwei
 * @version 14.1.0
 * @since 2018/8/29
 */
object InvestorContext {
    /**
     * 设置登录用户资方编号
     */
    var lendId: String
        get() = stringNotNull(ThreadContext["lendId"])
        set(lendId) = ThreadContext.put("lendId", lendId)


    var url: String
        get() = stringNotNull(ThreadContext["url"])
        set(url) = ThreadContext.put("url", url)


    /**
     * 本次会话ID
     * */
    var sessionId:String
        get() {
            return if (StringUtils.isNotEmpty(ThreadContext["sessionId"])){
                ThreadContext["sessionId"]
            }else{
                UUID.randomUUID().toString()
            }
        }
        set(value) = ThreadContext.put("sessionId", value)
    /**
     * 判断参数是否空值，如空值则转换为空字符串
     * @return "" 参数是null
     * 参数如果不是null，则返回原字符串
     */
    private fun stringNotNull(investorId: String?): String {
        return investorId ?: ""
    }

    fun isAdmin(): Boolean = lendId == "suadmin"
}
