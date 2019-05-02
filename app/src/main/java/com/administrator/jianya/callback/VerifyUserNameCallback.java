package com.administrator.jianya.callback;

import com.administrator.jianya.bean.VerifyBean;

/**
 * Created by Administrator on 2019/3/16.
 * 确认用户名唯一性的回调接口
 */

public interface VerifyUserNameCallback {

    void onVerifyUserName(VerifyBean verifyUserNameBean);

}
