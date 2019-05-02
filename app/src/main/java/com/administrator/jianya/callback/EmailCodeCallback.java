package com.administrator.jianya.callback;

import com.administrator.jianya.bean.VerifyBean;

/**
 * Created by Administrator on 2019/3/16.
 * 获取邮箱验证码的回调接口
 */

public interface EmailCodeCallback {

    void onError(VerifyBean body);

}
