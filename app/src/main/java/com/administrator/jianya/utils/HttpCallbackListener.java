package com.administrator.jianya.utils;

import java.io.IOException;

/**
 * Created by Administrator on 2019/1/30.
 */

interface HttpCallbackListener {
    void onFinish(String s);

    void onError(IOException e);
}
