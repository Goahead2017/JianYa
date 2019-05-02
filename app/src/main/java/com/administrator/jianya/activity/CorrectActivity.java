package com.administrator.jianya.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.administrator.jianya.R;

public class CorrectActivity extends AppCompatActivity {

    private LinearLayout correctEmail;
    private LinearLayout correctPassword;
    private LinearLayout correctRePassword;
    private LinearLayout correctYz;
    private Button correctButton;
    private TextView getYz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct);

        init();

        //动态设置控件位置
        LinearLayout.LayoutParams paramsEmail = (LinearLayout.LayoutParams) correctEmail.getLayoutParams();
        LinearLayout.LayoutParams paramPassword = (LinearLayout.LayoutParams) correctPassword.getLayoutParams();
        LinearLayout.LayoutParams paramRePassword = (LinearLayout.LayoutParams) correctRePassword.getLayoutParams();
        LinearLayout.LayoutParams paramYz = (LinearLayout.LayoutParams) correctYz.getLayoutParams();
        LinearLayout.LayoutParams paramBt = (LinearLayout.LayoutParams) correctButton.getLayoutParams();
        getYz.setGravity(Gravity.END);
        getYz.setPadding(0,0,5,0);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        paramsEmail.setMargins(width*75/1000, height*583/10000, width*75/1000,0);
        correctEmail.setLayoutParams(paramsEmail);
        paramPassword.setMargins(width*75/1000, height*401/10000, width*75/1000,0);
        correctPassword.setLayoutParams(paramPassword);
        paramRePassword.setMargins(width*75/1000, height*401/10000, width*75/1000,0);
        correctRePassword.setLayoutParams(paramRePassword);
        paramYz.setMargins(width*75/1000, height*474/10000, width*75/1000,0);
        correctYz.setLayoutParams(paramYz);
        paramBt.setMargins(width*2648/10000, height*3833/10000, width*2648/10000,0);
        correctButton.setLayoutParams(paramBt);
    }

    private void init() {
        correctEmail = findViewById(R.id.correct_email);
        correctPassword = findViewById(R.id.correct_password);
        correctRePassword = findViewById(R.id.correct_re_password);
        correctYz = findViewById(R.id.correct_yz);
        correctButton = findViewById(R.id.cr_button);
        getYz = findViewById(R.id.get_yz);
    }


}
