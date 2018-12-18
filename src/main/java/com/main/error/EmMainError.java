package com.main.error;

/**
 * @author LX
 * @date 2018/12/14 15:57
 */
public enum EmMainError implements CommonError {

    /**
     * 成功
     */
    SUCCESS(0, "success"),

    /**
     * 异常
     */
    ERROR(-1, "error msg"),

    /**
     * 参数不合法
     */
    KEY_ERROR(100001, "参数不合法"),
    UNKNOW_ERROR(100002, "未知错误"),

    /**
     * 用户不存在
     */
    USER_NOT_EXST(200001, "用户不存在");

    private int errCode;
    private String errMsg;

    private EmMainError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
