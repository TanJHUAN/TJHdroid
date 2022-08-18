package com.example.testdemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import com.example.tjhdroid.library.R;

public abstract class BaseActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewResId());
        ButterKnife.bind(this);
        initView();
    }

    protected abstract int setContentViewResId();

    protected abstract void initView();
}