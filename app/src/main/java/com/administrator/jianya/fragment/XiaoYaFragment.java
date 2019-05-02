package com.administrator.jianya.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.administrator.jianya.R;
import com.administrator.jianya.xiaoya.RecyclerViewAdapter;
import com.administrator.jianya.xiaoya.StaticData;
import com.administrator.jianya.xiaoya.lunbotu.ImageBarnnerFramLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/1/28.
 */

public class XiaoYaFragment extends Fragment implements ImageBarnnerFramLayout.FramLayoutLisenner {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    boolean isLoading;
    private List<Map<String, Object>> data ;
    private RecyclerViewAdapter adapter ;
    private Handler handler = new Handler();

    private int[] ids = new int[]{R.drawable.background_choose,R.drawable.background_login,R.drawable.background_register};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xiao_ya,container,false);

        //计算当前手机的宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        StaticData.WITTH = dm.widthPixels;

        ImageBarnnerFramLayout mGroup = view.findViewById(R.id.image_group);
        mGroup.setLisenner(this);
        List<Bitmap>list = new ArrayList<>();

        for (int id : ids) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            list.add(bitmap);
        }
        mGroup.addBitmaps(list);


        initView(view);
        initData();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler);
        swipeRefreshLayout = view.findViewById(R.id.swipe);
        data =  new ArrayList<>();
        adapter = new RecyclerViewAdapter(view.getContext(), data);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorCyan);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        getData();
                    }
                }, 2000);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("test", "onScrolled");

                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getData();
                                Log.d("test", "load more completed");
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });

        //添加点击事件
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("test", "item position = " + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }

            @Override
            public void onShuRu(TextView textView, int position) {
                Toast.makeText(getActivity(),textView.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 1500);

    }

    /**
     * 获取测试数据
     */
    private void getData() {
        for (int i = 0; i < 6; i++) {
            Map<String, Object> map = new HashMap<>();
            data.add(map);
        }
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyItemRemoved(adapter.getItemCount());
    }


    @Override
    public void clickImageIndex(int pos) {
        //实现轮播图具体图片的点击事件
        switch (pos){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
        Toast.makeText(getActivity(), "nihao" + pos, Toast.LENGTH_LONG).show();
    }
}
