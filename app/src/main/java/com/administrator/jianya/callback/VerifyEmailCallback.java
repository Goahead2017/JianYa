package com.administrator.jianya.callback;

import com.administrator.jianya.bean.VerifyBean;

/**
 * Created by Administrator on 2019/3/16.
 * 确认邮箱唯一性的回调接口
 */

public interface VerifyEmailCallback {

    void onVerityEmail(VerifyBean verifyEmailBean);

}
