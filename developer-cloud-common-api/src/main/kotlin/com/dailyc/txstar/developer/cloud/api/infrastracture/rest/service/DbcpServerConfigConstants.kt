package com.jd.jr.cpdp.common.infrastracture.rest.service

/**
 * 开发者平台服务配置项
 * @author Wang Weiwei / email : weiwei02@vip.qq.com
 * @since 2018/9/27
 * @version 2.0.0
 */
object ComponentServiceConstant{
    val name = "CPDP-COMPONENT-SERVICE"
    val baseUrl = "http://$name/"
    /**
     * 服务下的接口
     * */
    object interfaces{
        // 登录接口
        val loginUrl = "${baseUrl}user/login"

        // 日志保存接口
        val saveLogUrl = "${baseUrl}logs/save"
        // 已开通制定接口的资方列表
        val lendsByInterfaceUrl = "${baseUrl}app/interface/lends"
        // 根据id查询mock用户
        val mockUsersFindById = "${baseUrl}mockUser/findById"
    }
}

/**
 * 测试管理服务常量
 * */
object TestServiceConstant{
    val name = "cpdp-test-service"
    val baseUrl = "http://$name/"
    /**
     * 服务下的接口
     * */
    object interfaces{
        /**
         * 开发者测试消息
         * */
        val devMessage = "listener/dev"
        val devMessageUrl = "$baseUrl$devMessage"
        /**
         * 为开发者创建制定接口下的所有测试用例
         * */
        val createAllCaseByLendIdAndInterfaceName = "${baseUrl}case/interface/create"
        val createTestInfoForDev: String = "${baseUrl}case/init"
    }
}
/**
 * 现金贷服务常量
 * */
object XJDServiceConstant{
    val name = "cpdp-xjd-service"
    val baseUrl = "http://$name/"

    /**
     * 服务下的接口
     * */
    object interfaces{
        val createApply = "/checkApply/createOrder"
        val createApplyUrl = "$baseUrl$createApply"
    }
}

object FrontServiceConstant{
    val name = "cpdp-front-service"
    val baseUrl = "http://$name/front/"
    object interfaces{
        val login = "user/login"
        val indexUrl = "${baseUrl}index.html"
    }
}



