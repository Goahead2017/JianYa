package com.administrator.jianya.bean;

/**
 * Created by Administrator on 2019/3/16.
 * 封装验证用户名唯一性数据
 */

public class VerifyBean {

    /**
     * msg : string
     * object : {}
     * status : 0
     */

    private String msg;
    private ObjectBean object;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ObjectBean {
    }
}
