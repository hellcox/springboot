package com.main.common;

/**
 * @author LX
 * @date 2018/12/14 15:42
 */
public class MainReturn {
    private String status;
    private Object data;

    public static MainReturn create(Object result){
        return MainReturn.create(result,"success");
    }

    public static MainReturn create(Object result,String status){
        MainReturn type = new MainReturn();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
