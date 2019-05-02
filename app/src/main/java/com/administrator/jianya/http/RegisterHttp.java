package com.administrator.jianya.http;

import android.support.annotation.NonNull;
import android.util.Log;

import com.administrator.jianya.activity.RegisterActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
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
 * 注册网络请求
 */

public class RegisterHttp {

    private String email;
    private String userName;
    private String password;
    private String mailCode;
    private String rePassword;

    public RegisterHttp(RegisterActivity registerActivity,String email,String userName,String password,String mailCode,String rePassword){
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.mailCode = mailCode;
        this.rePassword = rePassword;
    }

    public interface GetRequest_Interface {

        @FormUrlEncoded
        @POST("register")
        Call<ResponseBody> getCall(@Field("eMail")String eMail, @Field("userName") String userName, @Field("password")String password, @Field("mailCode")String mailCode, @Field("rePassword")String rePassword);
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
        Call<ResponseBody> call = request.getCall(email,userName,password,mailCode,rePassword);


        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {
                    Log.d("register", "onResponse: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d("register", "onFailure: " + t.toString());
            }

        });
    }

}
