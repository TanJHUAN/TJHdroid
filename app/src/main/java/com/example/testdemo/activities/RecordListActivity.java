package com.example.testdemo.activities;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.testdemo.pojo.Record;
import com.example.tjhdroid.library.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecordListActivity extends BaseActivity {

    @BindView(R.id.rv_record_list)
    RecyclerView rvRecordList;

    BaseQuickAdapter baseQuickAdapter;
    List<Record> records = new ArrayList<>();
    private int currentIndex;


    @Override
    protected int setContentViewResId() {
        return R.layout.activity_record_list;
    }

    @Override
    protected void initView() {
        initData();
        rvRecordList.setLayoutManager(new GridLayoutManager(this, 1));
        baseQuickAdapter = new BaseQuickAdapter<Record,BaseViewHolder>(R.layout.item_record_list,records) {

            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, Record record) {
                TextView tvAccount = baseViewHolder.getView(R.id.tv_account);
                currentIndex = baseViewHolder.getLayoutPosition();
                tvAccount.setText(String.valueOf(currentIndex + 1));
            }
        };
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                Intent intent = new Intent(RecordListActivity.this, AnalysisResultActivity.class);
                startActivity(intent);
            }
        });
        rvRecordList.setAdapter(baseQuickAdapter);
    }

    private void initData(){
        for (int i = 0; i < 20; i++) {
            Record record = new Record();
            records.add(record);
        }
    }
}