package com.administrator.jianya.bean;

/**
 * Created by Administrator on 2019/3/16.
 * 封装登录数据
 */

public class LoginBean {


    /**
     * status : 1
     * msg : ok
     * object : {"userId":1,"userName":"admin","password":"1","email":"1@qq.com"}
     */

    private int status;
    private String msg;
    private ObjectBean object;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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

    public static class ObjectBean {
        /**
         * userId : 1
         * userName : admin
         * password : 1
         * email : 1@qq.com
         */

        private int userId;
        private String userName;
        private String password;
        private String email;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    @Override
    public String toString() {
        return  "\n" + "msg: " + getMsg() + "\n" +
                "email: " + getObject().getEmail() + "\n" +
                "password: " + getObject().getPassword() + "\n" +
                "userId: " + getObject().getUserId() + "\n" +
                "userName: " + getObject().getUserName() + "\n" +
                "status: " + getStatus();
    }

}
