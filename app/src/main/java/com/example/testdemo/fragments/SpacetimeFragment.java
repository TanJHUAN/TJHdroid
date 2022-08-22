package com.example.testdemo.fragments;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.testdemo.pojo.FlawRecord;
import com.example.tjhdroid.library.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SpacetimeFragment extends BaseFragment{

    @BindView(R.id.rv_flaw)
    RecyclerView rvFlaw;

    BaseQuickAdapter baseQuickAdapter;
    List<FlawRecord> flawRecords = new ArrayList<>();

    @Override
    public int setContentViewResId() {
        return R.layout.fragment_spacetime;
    }

    @Override
    public void initView() {
        testData();
        rvFlaw.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        baseQuickAdapter = new BaseQuickAdapter<FlawRecord, BaseViewHolder>(R.layout.item_flaw_record,flawRecords) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, FlawRecord flawRecord) {

            }
        };
        rvFlaw.setAdapter(baseQuickAdapter);

    }

    private void testData(){
        for (int i = 0; i < 20; i++) {
            FlawRecord flawRecord = new FlawRecord();
            flawRecord.setName("缺陷名称");
            flawRecord.setTime("2022-8-19");
            flawRecords.add(flawRecord);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
