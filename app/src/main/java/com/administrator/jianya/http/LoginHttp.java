package com.administrator.jianya.http;

import android.support.annotation.NonNull;
import android.util.Log;

import com.administrator.jianya.activity.LoginActivity;
import com.administrator.jianya.callback.LoginCallback;

import com.administrator.jianya.bean.LoginBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2019/3/15.
 * 登录网络请求
 */

public class LoginHttp {

    private Integer userId;
    private String password;
    private LoginCallback loginCallback;

    public LoginHttp(LoginActivity loginActivity,Integer userId,String password){
        this.userId = userId;
        this.password = password;
        this.loginCallback = loginActivity;
    }

    public interface GetRequest_Interface {

        @FormUrlEncoded
        @POST("login")
        Call<LoginBean> getCall(@Field("userId")Integer userId, @Field("password") String password);
        // 注解里传入 网络请求 的部分URL地址
        // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
        // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
        // getCall()是接受网络请求数据的方法
    }

    public  void request() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://113.251.174.41:8899/smile/userSystem/user/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava平台
                .build();

        // 步骤5:创建 网络请求接口 的实例
        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<LoginBean> call = request.getCall(userId,password);


        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(@NonNull Call<LoginBean> call, @NonNull Response<LoginBean> response) {
                loginCallback.onLogin(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<LoginBean> call, @NonNull Throwable t) {
                Log.d("login", "onFailure: " + t.toString());
            }

        });
    }

}
