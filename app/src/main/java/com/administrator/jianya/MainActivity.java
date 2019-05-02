package com.administrator.jianya;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.administrator.jianya.ViewPager.MyViewpager;
import com.administrator.jianya.ViewPager.ViewPagerChangedListenter;
import com.administrator.jianya.ViewPager.ViewPagerFragmentAdapter;
import com.administrator.jianya.fragment.DongYaFragment;
import com.administrator.jianya.fragment.ShuoYaFragment;
import com.administrator.jianya.fragment.XiaoYaFragment;
import com.administrator.jianya.tools.InvisibleBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //    菜单栏三个按钮
    private ImageView bt_xiao;
    private ImageView bt_dong;
    private ImageView bt_shuo;

    private ActionBarDrawerToggle drawerbar;
    public DrawerLayout drawerLayout;


    //    viewpager的对象，以及它的碎片适配器、碎片管理器、改变监听器。
    private MyViewpager viewPager;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private ViewPagerChangedListenter listenter;

    //    放三个碎片的容器
    private ArrayList<Fragment> fragments = new ArrayList<>();

    View headerView;        //抽屉顶部布局
    private ImageButton ceihua;
    private ImageButton touxiang;
    private TextView title;

    private static boolean isLogin = false;     //是否是登录状态。


    private XiaoYaFragment xiaoYaFragment;
    private ShuoYaFragment shuoYaFragment;
    private DongYaFragment dongYaFragment;
    //动态加载Fragment
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InvisibleBar.initStatusBar(getWindow());
        //初始化
        init();
        //设置顶部导航的点击监听
        setToolBarListener();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout,shuoYaFragment);
        fragmentTransaction.commit();
        init_Fragment();
        initPager();
    }

    private void setToolBarListener() {
        headerView = View.inflate(this,R.layout.content_layout,null);
        ceihua = findViewById(R.id.cebian);
        drawerLayout = findViewById(R.id.drawer);
        ceihua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void init() {
        xiaoYaFragment = new XiaoYaFragment();
        shuoYaFragment = new ShuoYaFragment();
        fragmentManager = getSupportFragmentManager();
        bt_xiao = findViewById(R.id.xiao);
        bt_dong = findViewById(R.id.dong);
        bt_shuo = findViewById(R.id.shuo);
        touxiang = findViewById(R.id.profile_image);
        /*touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FirstActivity.class);
                startActivity(intent);
            }
        });*/

        bt_xiao.setOnClickListener(this);
        bt_dong.setOnClickListener(this);
        bt_shuo.setOnClickListener(this);

        title = findViewById(R.id.title);
        title.setText("笑呀");
    }

    private void init_Fragment(){
        Fragment f1 = new XiaoYaFragment();
        Fragment f2 = new DongYaFragment();
        Fragment f3 = new ShuoYaFragment();

        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
    }

    private void initPager(){
        listenter = new ViewPagerChangedListenter();
        fragmentManager = getSupportFragmentManager();
        viewPager = (MyViewpager) findViewById(R.id.layout);
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(fragmentManager,fragments);

        listenter.setContext(this);

        viewPager.setScanScroll(false);
        viewPager.setAdapter(viewPagerFragmentAdapter);
        viewPager.addOnPageChangeListener(listenter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.xiao:
                viewPager.setCurrentItem(0);
                //设置按键的图片随点击而发生改变
                bt_xiao.setImageDrawable(getResources().getDrawable(R.drawable.xiao_selected));
                bt_dong.setImageDrawable(getResources().getDrawable(R.drawable.dong_logo));
                bt_shuo.setImageDrawable(getResources().getDrawable(R.drawable.shuo_logo));
                title.setText("笑呀");
                break;
            case R.id.dong:
                viewPager.setCurrentItem(1);
                //设置按键的图片随点击而发生改变
                bt_xiao.setImageDrawable(getResources().getDrawable(R.drawable.xiao_logo));
                bt_dong.setImageDrawable(getResources().getDrawable(R.drawable.dong_selected));
                bt_shuo.setImageDrawable(getResources().getDrawable(R.drawable.shuo_logo));
                title.setText("动呀");
                break;
            case R.id.shuo:
                viewPager.setCurrentItem(2);
                //设置按键的图片随点击而发生改变
                bt_xiao.setImageDrawable(getResources().getDrawable(R.drawable.xiao_logo));
                bt_dong.setImageDrawable(getResources().getDrawable(R.drawable.dong_logo));
                bt_shuo.setImageDrawable(getResources().getDrawable(R.drawable.shuo_selected));
                title.setText("说呀");
                break;
            default:
                break;
        }
    }
}
