package com.example.testdemo.activities;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.testdemo.fragments.ElectronicPatrolFragment;
import com.example.testdemo.fragments.SettingsFragment;
import com.example.testdemo.fragments.SpacetimeFragment;
import com.example.testdemo.fragments.StatePatrolFragment;
import com.example.testdemo.pojo.PatrolRecord;
import com.example.tjhdroid.library.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    //菜单栏
    @BindView(R.id.ll_guowang_xuncha)
    LinearLayout llGuowangXuncha;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.ll_dianchang_xuncha)
    LinearLayout llDianchangXuncha;
    @BindView(R.id.ll_settings)
    LinearLayout llSettings;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_search_view)
    LinearLayout searchView;



    StatePatrolFragment statePatrolFragment;
    SpacetimeFragment spacetimeFragment;
    ElectronicPatrolFragment electronicPatrolFragment;
    SettingsFragment settingsFragment;
    Fragment currentFragment;




    @Override
    protected int setContentViewResId() {
        return R.layout.activity_home;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initView() {
        statePatrolFragment = new StatePatrolFragment();
        spacetimeFragment = new SpacetimeFragment();
        electronicPatrolFragment = new ElectronicPatrolFragment();
        settingsFragment = new SettingsFragment();
        currentFragment = new Fragment();
        switchFragment(statePatrolFragment);

    }


    //切换fragment
    private void switchFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()){
            fragmentTransaction.hide(currentFragment).add(R.id.rl_content,fragment).commit();
        }else {
            fragmentTransaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    @OnClick({R.id.ll_guowang_xuncha,R.id.ll_search,R.id.ll_dianchang_xuncha,R.id.ll_settings})
    public void switchFragments(View view){
        switch (view.getId()){
            case R.id.ll_guowang_xuncha:
                switchFragment(statePatrolFragment);
                llGuowangXuncha.setBackground(getResources().getDrawable(R.color.grey));
                llSearch.setBackground(getResources().getDrawable(R.color.white));
                llDianchangXuncha.setBackground(getResources().getDrawable(R.color.white));
                llSettings.setBackground(getResources().getDrawable(R.color.white));
                searchView.setVisibility(View.GONE);
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText("国网巡查");
                break;
            case R.id.ll_search:
                switchFragment(spacetimeFragment);
                llSearch.setBackground(getResources().getDrawable(R.color.grey));
                llGuowangXuncha.setBackground(getResources().getDrawable(R.color.white));
                llDianchangXuncha.setBackground(getResources().getDrawable(R.color.white));
                llSettings.setBackground(getResources().getDrawable(R.color.white));
                searchView.setVisibility(View.VISIBLE);
                tvTitle.setVisibility(View.GONE);
                break;
            case R.id.ll_dianchang_xuncha:
                switchFragment(electronicPatrolFragment);
                llDianchangXuncha.setBackground(getResources().getDrawable(R.color.grey));
                llGuowangXuncha.setBackground(getResources().getDrawable(R.color.white));
                llSearch.setBackground(getResources().getDrawable(R.color.white));
                llSettings.setBackground(getResources().getDrawable(R.color.white));
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText("电厂巡查");
                searchView.setVisibility(View.GONE);
                break;
            case R.id.ll_settings:
                switchFragment(settingsFragment);
                llSettings.setBackground(getResources().getDrawable(R.color.grey));
                llGuowangXuncha.setBackground(getResources().getDrawable(R.color.white));
                llSearch.setBackground(getResources().getDrawable(R.color.white));
                llDianchangXuncha.setBackground(getResources().getDrawable(R.color.white));
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText("系统设置");
                searchView.setVisibility(View.GONE);
                break;
        }
    }

}