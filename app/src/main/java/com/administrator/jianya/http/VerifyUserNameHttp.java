package com.administrator.jianya.http;

import android.support.annotation.NonNull;
import android.util.Log;

import com.administrator.jianya.activity.RegisterActivity;
import com.administrator.jianya.callback.VerifyUserNameCallback;

import com.administrator.jianya.bean.VerifyBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2019/3/15.
 * 验证用户名唯一性网络请求
 */

public class VerifyUserNameHttp {

    private String userName;
    private VerifyUserNameCallback verifyUserNameCallback;

    public VerifyUserNameHttp(RegisterActivity registerActivity,String userName){
        this.userName = userName;
        this.verifyUserNameCallback = registerActivity;
    }

    public interface GetRequest_Interface {

        @GET("verifyUserName")
        Call<VerifyBean> getCall(@Query("userName") String userName);
        // 注解里传入 网络请求 的部分URL地址
        // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
        // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
        // getCall()是接受网络请求数据的方法
    }

    public  void request() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://113.251.174.41:8899/smile/userSystem/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava平台
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<VerifyBean> call = request.getCall(userName);


        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<VerifyBean>() {
            @Override
            public void onResponse(@NonNull Call<VerifyBean> call, @NonNull Response<VerifyBean> response) {
                Log.d("verify", "onResponse: " + response.toString());
                verifyUserNameCallback.onVerifyUserName(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<VerifyBean> call, @NonNull Throwable t) {
                Log.d("register", "onFailure: " + t.toString());
            }

        });
    }

}
