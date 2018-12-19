package com.main.error;

/**
 * @author long
 * @date 2018/12/14 15:55
 */
public interface CommonError {

    public int getErrCode();

    public String getErrMsg();

    public CommonError setErrMsg(String errMsg);
}
