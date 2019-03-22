package com.jd.jr.cpdp.common.infrastracture.rest.service

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import org.springframework.core.io.ClassPathResource
import java.nio.charset.Charset

/**
 * 开发者平台接口定义工具
 * @author Wang Weiwei / email : weiwei02@vip.qq.com
 * @since 2018/10/8
 * @version 2.0.0
 */

object InterfaceDefine {
    private var interfaceStr = ""
    private var interfaceObject: JSONObject = JSONObject()
    private val xjdObject = JSONObject()

    init {
        val interfaceConfigInput = ClassPathResource("/config/appInterfaces.json").inputStream
        val bytes = ByteArray(interfaceConfigInput.available())
        interfaceConfigInput.read(bytes)
        interfaceStr = String(bytes, Charset.forName("UTF-8"))
        interfaceObject = JSON.parseObject(interfaceStr)

        // 获取现金贷接口对象
        val xjd = interfaceObject.getJSONArray("xjd")
        for (i in xjd.indices){
            val xjdInterface = xjd.getJSONObject(i)
            xjdObject[xjdInterface.getString("interfaceName")] = xjdInterface
        }
    }


    /**
     * 获取现金贷接口详情
     * @param url 现金贷接口地址
     * @return DevPlatformInterface 开发者平台接口对象
     * */
    fun interfaceDetailXjd(url: InvestorUrl) : DevPlatformInterface =
            DevPlatformInterface(xjdObject.getJSONObject(url.code))
    fun interfaceDetailXjd(url: String) : DevPlatformInterface =
            DevPlatformInterface(xjdObject.getJSONObject(url))


    /**
     * 获取所有现金贷  资方类接口列表
     * */
    fun investorListXjd(): ArrayList<DevPlatformInterface>{
        val list = ArrayList<DevPlatformInterface>()
        val keys = xjdObject.keys
        for (url in keys){
            val interfaces = DevPlatformInterface(xjdObject.getJSONObject(url))
            if (interfaces.isInvestor()) list.add(interfaces)
        }
        return list
    }


    /**
     * 列出所有现金贷申请类接口
     * @return 申请类接口列表
     * */
    fun listXjdApplyInterfaces(): List<DevPlatformInterface>  = arrayListOf(
            interfaceDetailXjd(InvestorUrl.checkApply),
            interfaceDetailXjd(InvestorUrl.checkApplyResult),
            interfaceDetailXjd(InvestorUrl.queryCheckApplyResult),
            interfaceDetailXjd(InvestorUrl.preRepaymentPlan)
        )

    /**
     * 列出所有现金贷资方放款类接口列表
     * @return 资方放款类接口列表
     * */
    fun listXjdInvestorPaymentInterfaces(): List<DevPlatformInterface>  = arrayListOf(
            interfaceDetailXjd(InvestorUrl.payment),
            interfaceDetailXjd(InvestorUrl.paymentResult),
            interfaceDetailXjd(InvestorUrl.queryPaymentResult)
        )

    /**
     * 列出所有现金贷平台放款类接口列表
     * @return 平台放款类接口列表
     * */
    fun listXjdPlatformPaymentInterfaces(): List<DevPlatformInterface>  = arrayListOf(
            interfaceDetailXjd(InvestorUrl.checkPayment),
            interfaceDetailXjd(InvestorUrl.syncPayment),
            interfaceDetailXjd(InvestorUrl.syncPaymentResult)
        )

    /**
     * 列出所有现金贷平台还款类接口列表
     * @return 还款类接口列表
     * */
    fun listXjdRepaymentInterfaces(): List<DevPlatformInterface>  = arrayListOf(
            interfaceDetailXjd(InvestorUrl.queryRepaymentPlan),
            interfaceDetailXjd(InvestorUrl.queryRepaymentRealAmount),
            interfaceDetailXjd(InvestorUrl.syncRepayment),
            interfaceDetailXjd(InvestorUrl.syncRepaymentResult)
        )

    /**
     * 列出所有现金贷增强类接口列表
     * @return 增强类接口列表
     * */
    fun listXjdAdvanceInterfaces(): List<DevPlatformInterface>  = arrayListOf(
            interfaceDetailXjd(InvestorUrl.preCancelAccount),
            interfaceDetailXjd(InvestorUrl.cancelAccount),
            interfaceDetailXjd(InvestorUrl.queryCreditAmount),
            interfaceDetailXjd(InvestorUrl.notifyCreditAmount),
            interfaceDetailXjd(InvestorUrl.changeWithholdCard)
        )
}