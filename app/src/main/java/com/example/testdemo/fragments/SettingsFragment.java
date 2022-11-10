package com.example.testdemo.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.example.testdemo.activities.AboutActivity;
import com.example.tjhdroid.library.R;

public class SettingsFragment extends BaseFragment{

    @BindView(R.id.rl_about)
    RelativeLayout rlAbout;

    @Override
    public int setContentViewResId() {
        return R.layout.fragment_settings;
    }

    @Override
    public void initView() {
        rlAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }


}
