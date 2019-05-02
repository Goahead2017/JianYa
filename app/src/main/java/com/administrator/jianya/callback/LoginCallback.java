package com.administrator.jianya.callback;

import com.administrator.jianya.bean.LoginBean;

/**
 * Created by Administrator on 2019/3/16.
 * 登录的回调接口
 */

public interface LoginCallback {

    void onLogin(LoginBean loginBean);

}
