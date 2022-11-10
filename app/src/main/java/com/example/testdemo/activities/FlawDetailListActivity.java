package com.example.testdemo.activities;

import android.graphics.ColorMatrixColorFilter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.testdemo.pojo.FlawDetailList;
import com.example.testdemo.pojo.FlawList;
import com.example.tjhdroid.library.R;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

public class FlawDetailListActivity extends BaseActivity {

    @BindView(R.id.rv_flaw_detail_list)
    RecyclerView rvFlawDetailList;

    BaseQuickAdapter baseQuickAdapter;

    List<FlawDetailList> flawDetailLists = new ArrayList<>();
    @Override
    protected int setContentViewResId() {
        return R.layout.activity_flaw_detail_list;
    }

    @Override
    protected void initView() {
        init();
        rvFlawDetailList.setLayoutManager(new GridLayoutManager(this,5));
        baseQuickAdapter = new BaseQuickAdapter<FlawDetailList, BaseViewHolder>(R.layout.item_flaw_detail_list,flawDetailLists) {

            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, FlawDetailList flawList) {

            }
        };
        rvFlawDetailList.setAdapter(baseQuickAdapter);
    }

    private void init(){
        for (int i = 0; i < 20; i++) {
            FlawDetailList flawDetailList = new FlawDetailList();
            flawDetailList.setCreatTime("2022-8-24");
            flawDetailLists.add(flawDetailList);
        }
    }
}