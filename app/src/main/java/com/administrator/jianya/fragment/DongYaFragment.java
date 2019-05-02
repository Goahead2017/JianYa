package com.administrator.jianya.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.administrator.jianya.R;

/**
 * Created by Administrator on 2019/1/28.
 */

public class DongYaFragment extends Fragment{

    private ImageButton img_three;
    private ImageButton img_game;
    private ImageButton img_music;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dong_ya,container,false);

        init(view);
        return view;
    }

    public void init(View view){
        img_three = view.findViewById(R.id.three_minutes);
        img_game = view.findViewById(R.id.game_choose);
        img_music = view.findViewById(R.id.music_choose);

        /*img_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_three.class);
            }
        });

        img_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(context, Activity_game.class);

            }
        });

        img_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(context, Activity_music.class);
            }
        });*/
    }
}
