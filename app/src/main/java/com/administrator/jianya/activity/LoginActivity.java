package com.administrator.jianya.activity;

import android.content.Intent;
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
import com.administrator.jianya.callback.LoginCallback;
import com.administrator.jianya.http.LoginHttp;
import com.administrator.jianya.tools.InvisibleBar;

import com.administrator.jianya.bean.LoginBean;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,LoginCallback{

   private LinearLayout loginUser;
   private LinearLayout loginPassword;

   private Button btLogin;
   private Button btRegister;
   private TextView btCorrect;

   //获取用户id和密码
    private EditText userId;
    private EditText userPassword;
    private Integer id;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InvisibleBar.initStatusBar(getWindow());
        //初始化
        init();
        //动态设置控件位置
        dyDraw();

    }

    private void dyDraw(){
        //动态设置控件位置
        LinearLayout.LayoutParams paramsUser = (LinearLayout.LayoutParams) loginUser.getLayoutParams();
        LinearLayout.LayoutParams paramPassword = (LinearLayout.LayoutParams) loginPassword.getLayoutParams();
        WindowManager manager = this.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        paramsUser.setMargins(width*2213/10000, height*4641/10000, width*2213/10000,0);
        loginUser.setLayoutParams(paramsUser);
        paramPassword.setMargins(width*2213/10000, height*661/10000, width*2213/10000,0);
        loginPassword.setLayoutParams(paramPassword);
    }

    private void init() {
        loginUser = findViewById(R.id.login_user);
        loginPassword = findViewById(R.id.login_password);

        btLogin = findViewById(R.id.bt_login);
        btRegister = findViewById(R.id.bt_register);
        btCorrect = findViewById(R.id.bt_correct);
        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        btCorrect.setOnClickListener(this);

        userId = findViewById(R.id.user_id);
        userPassword = findViewById(R.id.user_password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //设置登录按钮的点击事件
            case R.id.bt_login:
                if(userId.getText().length() != 0){
                    id = Integer.parseInt(userId.getText().toString());
                }
                password = userPassword.getText().toString();
                if(id == null || password.length() == 0){
                    Toast.makeText(this,"请先把信息填完整",Toast.LENGTH_SHORT).show();
                }else {
                    //new RegisterHttp().request();
                    Log.d("login", "onClick: " + id + "," + password);
                    new LoginHttp(this,id,password).request();
                }
                break;
            case R.id.bt_register:
                Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_correct:
                Intent intent2 = new Intent(LoginActivity.this,CorrectActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        if(loginBean.getStatus() == 1){
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            Log.d("login", "onResponse: " + loginBean.toString());
        }else {
            Toast.makeText(this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
            Log.d("login", "onResponse: " + "\n" + loginBean.getMsg());
        }

    }
}
