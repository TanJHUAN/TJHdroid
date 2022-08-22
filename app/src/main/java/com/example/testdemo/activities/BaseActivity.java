package com.example.testdemo.activities;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.tjhdroid.library.R;


public abstract class BaseActivity extends FragmentActivity {

    @BindView(R.id.iv_back)
    @Nullable
    protected View ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(setContentViewResId());
        ButterKnife.bind(this);
        if(ivBack != null)
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        initView();
    }

    protected abstract int setContentViewResId();

    protected abstract void initView();
}