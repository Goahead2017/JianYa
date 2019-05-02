package com.administrator.jianya.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.administrator.jianya.R;
import com.administrator.jianya.callback.EmailCodeCallback;
import com.administrator.jianya.callback.RegisterCallback;
import com.administrator.jianya.callback.VerifyEmailCallback;
import com.administrator.jianya.callback.VerifyUserNameCallback;
import com.administrator.jianya.http.EmailCodeHttp;
import com.administrator.jianya.http.RegisterHttp;
import com.administrator.jianya.http.VerifyEmailHttp;
import com.administrator.jianya.http.VerifyUserNameHttp;
import com.administrator.jianya.tools.InvisibleBar;

import com.administrator.jianya.bean.LoginBean;
import com.administrator.jianya.bean.VerifyBean;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,EmailCodeCallback,VerifyUserNameCallback,VerifyEmailCallback,RegisterCallback{

    private LinearLayout registerUser;
    private LinearLayout registerPassword;
    private LinearLayout registerRePassword;
    private LinearLayout registerEmail;
    private LinearLayout registerYzEmail;

    //获取用户相关信息
    private EditText userName;
    private EditText userPassword;
    private EditText userRePassword;
    private EditText userMail;
    private EditText userYz;
    private TextView btYz;
    private Button btRegister;

    private String name;
    private String password;
    private String rePassword;
    private String mail;
    private String yz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        InvisibleBar.initStatusBar(getWindow());
        //初始化
        init();

        //动态设置控件位置
        dyDraw();

    }

    //动态设置控件位置
    private void dyDraw() {
        LinearLayout.LayoutParams paramsUser = (LinearLayout.LayoutParams) registerUser.getLayoutParams();
        LinearLayout.LayoutParams paramsPassword = (LinearLayout.LayoutParams) registerPassword.getLayoutParams();
        LinearLayout.LayoutParams paramsRePassword = (LinearLayout.LayoutParams) registerRePassword.getLayoutParams();
        LinearLayout.LayoutParams paramsEmail = (LinearLayout.LayoutParams) registerEmail.getLayoutParams();
        LinearLayout.LayoutParams paramsYzEmail = (LinearLayout.LayoutParams) registerYzEmail.getLayoutParams();
        WindowManager manager = this.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        paramsUser.setMargins(width*1200/10000, height*3995/10000, width*1200/10000,0);
        registerUser.setLayoutParams(paramsUser);
        paramsPassword.setMargins(width*1200/10000, height*427/10000, width*1200/10000,0);
        registerPassword.setLayoutParams(paramsPassword);
        paramsRePassword.setMargins(width*1200/10000, height*427/10000, width*1200/10000,0);
        registerRePassword.setLayoutParams(paramsRePassword);
        paramsEmail.setMargins(width*1200/10000, height*427/10000, width*1200/10000,0);
        registerEmail.setLayoutParams(paramsEmail);
        paramsYzEmail.setMargins(width*1200/10000, height*427/10000, width*1200/10000,0);
        registerYzEmail.setLayoutParams(paramsYzEmail);
    }

    private void init() {
        registerUser = findViewById(R.id.register_user);
        registerPassword = findViewById(R.id.register_password);
        registerRePassword = findViewById(R.id.register_re_password);
        registerEmail = findViewById(R.id.register_email);
        registerYzEmail = findViewById(R.id.register_yz_email);

        userName = findViewById(R.id.user_name);
        userPassword = findViewById(R.id.user_password);
        userRePassword = findViewById(R.id.user_re_password);
        userMail = findViewById(R.id.user_mail);
        userYz = findViewById(R.id.user_yz);
        btYz = findViewById(R.id.bt_getYz);
        btRegister = findViewById(R.id.bt_register);
        btYz.setOnClickListener(this);
        btRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //设置注册按钮的点击事件
            case R.id.bt_getYz:
                mail = userMail.getText().toString();
                if(mail.length() == 0){
                    Toast.makeText(this,"请先填写邮箱地址",Toast.LENGTH_SHORT).show();
                }else {
                    new EmailCodeHttp(this,mail).request();
                }
                break;
            case R.id.bt_register:
                name = userName.getText().toString();
                password = userPassword.getText().toString();
                rePassword = userRePassword.getText().toString();
                mail = userMail.getText().toString();
                yz = userYz.getText().toString();
                if(name.length() == 0 || password.length() == 0 || rePassword.length() == 0 || mail.length() == 0 || yz.length() == 0){
                    Toast.makeText(this,"请先把信息填写完整",Toast.LENGTH_SHORT).show();
                }else {
                    new VerifyUserNameHttp(this,name).request();
                }
                break;
        }
    }

    @Override
    public void onVerifyUserName(VerifyBean verifyUserNameBean) {
        Log.d("verify", "onVerifyUserName: " + verifyUserNameBean.getStatus() + "\n" + verifyUserNameBean.getMsg());
        if(verifyUserNameBean.getStatus() == 1){
            new VerifyEmailHttp(this,mail).request();
        }else {
            Toast.makeText(this,"用户名已存在",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onVerityEmail(VerifyBean verifyEmailBean) {
        Log.d("verify", "onVerifyUserName: " + verifyEmailBean.getStatus() + "\n" + verifyEmailBean.getMsg());
        if(verifyEmailBean.getStatus() == 1){
            new RegisterHttp(this,mail,name,password,yz,rePassword).request();
        }else {
            Toast.makeText(this,"邮箱已注册",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegister(LoginBean loginBean) {

    }

    @Override
    public void onError(VerifyBean emailCode) {
        if(emailCode.getStatus() == 4){
            Toast.makeText(this,emailCode.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }
}
