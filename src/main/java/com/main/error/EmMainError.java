package com.main.error;

/**
 * @author long
 * @date 2018/12/14 15:57
 */
public enum EmMainError implements CommonError {

    /**
     * 成功
     */
    SUCCESS(0, "success"),

    /**
     * 失败
     */
    FAIL(-1, "fail msg"),

    /**
     * 未知错误
     */
    ERROR_UNKNOWN(100000, "未知错误"),
    KEY_ERROR(100002, "参数不合法");

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
