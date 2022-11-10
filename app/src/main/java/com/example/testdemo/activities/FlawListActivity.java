package com.example.testdemo.activities;

import android.content.Intent;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.testdemo.pojo.FlawList;
import com.example.tjhdroid.library.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FlawListActivity extends BaseActivity {

    @BindView(R.id.rv_flaw_list)
    RecyclerView rvFlawList;

    BaseQuickAdapter baseQuickAdapter;

    List<FlawList> flawLists = new ArrayList<>();

    public final float[] BG_PRESSED = new float[] { 1, 0, 0, 0, -100, 0, 1, 0, 0, -100, 0, 0, 1, 0, -100, 0, 0, 0, 1, 0 };

    @Override
    protected int setContentViewResId() {
        return R.layout.activity_flaw_list;
    }

    @Override
    protected void initView() {
        initData();
        rvFlawList.setLayoutManager(new GridLayoutManager(this,5));
        baseQuickAdapter = new BaseQuickAdapter<FlawList,BaseViewHolder>(R.layout.item_flaw_list,flawLists) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, FlawList flawList) {
                TextView tvNum = baseViewHolder.getView(R.id.tv_num);
                tvNum.setText("0"+flawList.getGroupNum());
                ImageView ivBg = baseViewHolder.getView(R.id.iv_bg);
                ivBg.setColorFilter(new ColorMatrixColorFilter(BG_PRESSED));
                TextView tvRecordNum = baseViewHolder.getView(R.id.tv_record_num);
                tvRecordNum.setText(flawList.getFlawNum());
            }
        };

        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                Intent intent = new Intent(FlawListActivity.this, FlawDetailListActivity.class);
                startActivity(intent);
            }
        });
        rvFlawList.setAdapter(baseQuickAdapter);

    }

    private void initData(){
        for (int i = 0; i < 20; i++) {
            FlawList flawList = new FlawList();
            flawList.setGroupNum(String.valueOf(i+1));
            flawList.setFlawNum("100条记录");
            flawLists.add(flawList);
        }
    }
}