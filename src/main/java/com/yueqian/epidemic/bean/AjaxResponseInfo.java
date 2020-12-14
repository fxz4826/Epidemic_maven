package com.yueqian.epidemic.bean;

//该类是ajax请求响应给页面的json格式的对象
public class AjaxResponseInfo <T> {

    private int code;   //请求成功的响应码  0表示成功，-1表示请求失败  -2表示权限不足
    private String msg;  //给页面响应的字符串信息
    private T data;    //给页面响应的具体的数据

    public AjaxResponseInfo() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AjaxResponseInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
