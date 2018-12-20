package com.main.common;

import java.util.HashMap;

/**
 * @author long
 * @date 2018/12/14 15:42
 */
public class MainReturn {
    private Integer code;
    private String msg;
    private Object data;

    public static MainReturn success() {
        return MainReturn.create(new HashMap<>(), 0, "success");
    }

    public static MainReturn success(Object result) {
        return MainReturn.create(result, 0, "success");
    }

    public static MainReturn success(Object result, String msg) {
        return MainReturn.create(result, 0, msg);
    }

    public static MainReturn fail(Object result, Integer code, String msg) {
        return MainReturn.create(result, code, msg);
    }

    public static MainReturn error(Object result, Integer code, String msg) {
        return MainReturn.create(result, code, msg);
    }

    public static MainReturn create(Object result, Integer code, String msg) {
        MainReturn type = new MainReturn();
        type.setMsg(msg);
        type.setData(result);
        type.setCode(code);
        return type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
