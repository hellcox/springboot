package com.main.error;

/**
 * @author LX
 * @date 2018/12/14 16:03
 *
 * 包装类异常处理器
 */
public class MainException extends Exception implements CommonError{

    private CommonError commonError;

    /**
     * 直接接收传参用于够着业务异常
     * @param commonError
     */
    public MainException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    /**
     * 接收自定义errMsg的方式构造业务异常
     * @return
     */
    public MainException(CommonError commonError, String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }


    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
