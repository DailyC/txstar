package com.dailyc.txstar.developer.cloud.api.infrastracture.rest

import com.alibaba.fastjson.JSONObject

/**
 * Created by zhangxin19 on 2017/7/5.
 */
enum class ResponseCode private constructor(var code: String, var msg: String) {

    SUCCESS("00000", "成功"),

    // AutoMock使用
    DATA_BASE_ERROR("DB00001", "数据库异常"),
    DATA_BASE_QUERY_ERROR("DB00002", "未找到相关信息"),
    HTTP_ERROR("HTTP00001", "网络异常"),

    /**
     * 开发者平台异常
     * */
    REST_USER_STATUS_ERROR("COM00001", "账户状态异常，请联系客服！"),
    REST_USER_PASSOWRD_ERROR("COM00002", "用户名或密码错误！"),
    REST_USER_LOGIN_ERROR("COM00003", "请先登录！"),
    REST_SYS_ERROR("COM00004", "系统开小差了，请联系管理员！"),
    REST_SYS_NULL_ERROR("COM00005", "您访问的页面走丢了，请稍后访问!"),
    REST_NO_PERMISSION_ERROR("COM00006", "权限不足!"),

    /**
     * 组件服务
     * */
    REST_COM_NULL_APP("COM55555", "应用未创建，请先创建应用！"),
    REST_COM_REPEAT_APP("COM55556", "数据重复，请检测！"),
    REST_COM_LOG_SAVE("COM55557", "日志保存失败！"),

    /**
     * 开发者交互使用
     * */
    AKS_EN_ERROR("AKS00001", "制作数字信封异常"),
    AKS_DE_ERROR("AKS00002", "解密数字信封异常"),
    SYSTEM_PARAM_ERROR("SYS00002", "参数错误"),
    SYSTEM_ERROR("SYS00001", "系统异常");

    fun toJSONString(): String = JSONObject.toJSONString(this)
}
