package com.jd.jr.cpdp.common.infrastracture.rest.service

/**
 *资方交互接口地址枚举
 * @author Wang Weiwei
 * @since 2018/9/5
 * @version 2.0.0
 */
enum class InvestorUrl(val code: String, val desc: String) {
    // 申请类
    checkApply("checkApply", "审核申请"),
    checkApplyResult("checkApplyResult", "审核结果通知"),
    queryCheckApplyResult("queryCheckApplyResult", "查询审核结果"),
    preRepaymentPlan("preRepaymentPlan", "试算还款计划"),

    // 平台放款类
    checkPayment("checkPayment", "提现审核申请(平台放款)"),
    syncPayment("syncPayment", "提现结果通知(平台放款 - 平台 TO 资方)"),
    syncPaymentResult("syncPaymentResult", "资方处理结果通知（平台放款 - 资方 T O  平台）"),

    // 资方放款类
    payment("payment", "提现申请(资方放款)"),
    paymentResult("paymentResult", "提现结果通知(资方放款 - 资方 TO 平台)"),
    queryPaymentResult("queryPaymentResult", "查询提现结果(资方放款)"),

    // 还款类
    queryRepaymentPlan("queryRepaymentPlan", "查询还款计划"),
    queryRepaymentRealAmount("queryRepaymentRealAmount", "还款应收查询"),
    syncRepayment("syncRepayment", "还款结果通知(平台 TO 资方)"),
    syncRepaymentResult("syncRepaymentResult", "资方处理结果通知(资方 TO 平台)"),


    // 销户类
    preCancelAccount("preCancelAccount", "预销户（平台 to 资方）"),
    cancelAccount("cancelAccount", "销户（平台 to 资方）"),

    // 增强类接口
    queryCreditAmount("queryCreditAmount", "查询实时额度"),
    notifyCreditAmount("notifyCreditAmount", "额度变动通知"),
    changeWithholdCard("changeWithholdCard", "银行卡变动通知"),

    // 加解密
    getCrplCert("getCrplCert", "获取平台公钥证书"),
    getInvCert("getInvCert", "获取资方公钥证书"),
    ;

    companion object {

        fun codeToEnum(code: String): InvestorUrl? {
            for (status in values()) {
                if (status.code == code) {
                    return status
                }
            }
            return null
        }
    }
}
