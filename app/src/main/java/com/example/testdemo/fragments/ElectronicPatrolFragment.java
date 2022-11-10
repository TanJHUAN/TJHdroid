package com.example.testdemo.fragments;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.testdemo.activities.FlawListActivity;
import com.example.testdemo.activities.MainActivity;
import com.example.testdemo.pojo.PatrolRecord;
import com.example.testdemo.utils.TimePickerUtils;
import com.example.tjhdroid.library.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ElectronicPatrolFragment extends BaseFragment{
    @BindView(R.id.ll_time_start)
    LinearLayout llTimeStart;

    @BindView(R.id.iv_my_location)
    ImageView myLocation;

    @BindView(R.id.ll_time_end)
    LinearLayout llTimeEnd;

    @BindView(R.id.tv_time_start)
    TextView tvTimeStart;

    @BindView(R.id.tv_time_end)
    TextView tvTimeEnd;

    @BindView(R.id.rv_patrol)
    RecyclerView rvPatrol;

    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    BaseQuickAdapter baseQuickAdapter;
    List<PatrolRecord> patrolRecords = new ArrayList<>();
    @Override
    public int setContentViewResId() {
        return R.layout.fragment_state_patrol;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.ll_time_start,R.id.ll_time_end,R.id.iv_my_location})
    public void showTimePicker(View view){
        switch (view.getId()){
            case R.id.ll_time_start:
                TimePickerUtils.showDatePickerDialog(getActivity(),0,tvTimeStart,calendar);
                break;
            case R.id.ll_time_end:
                TimePickerUtils.showDatePickerDialog(getActivity(),0,tvTimeEnd,calendar);
                break;
            case R.id.iv_my_location:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
        }
    }
    @Override
    public void initView() {
        testData();
        rvPatrol.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        baseQuickAdapter = new BaseQuickAdapter<PatrolRecord, BaseViewHolder>(R.layout.item_patrol_record,patrolRecords) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, PatrolRecord patrolRecord) {

            }
        };
        rvPatrol.setAdapter(baseQuickAdapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.blue_shallow);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 测试数据
     */
    private void testData(){
        for (int i = 0; i < 20; i++) {
            PatrolRecord patrolRecord = new PatrolRecord();
            patrolRecord.setTime("2020-02-02");
            patrolRecords.add(patrolRecord);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
