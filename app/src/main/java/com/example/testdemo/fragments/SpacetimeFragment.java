package com.example.testdemo.fragments;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.testdemo.activities.FlawListActivity;
import com.example.testdemo.pojo.FlawRecord;
import com.example.tjhdroid.library.R;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpacetimeFragment extends BaseFragment{

    @BindView(R.id.rv_flaw)
    RecyclerView rvFlaw;

    BaseQuickAdapter baseQuickAdapter;
    List<FlawRecord> flawRecords = new ArrayList<>();

    @BindView(R.id.ll_data_class_search)
    LinearLayout llDataSearch;

    @BindView(R.id.ll_data)
    LinearLayout llData;

    @BindView(R.id.ll_search_bar)
    LinearLayout llSearchBar;

    @BindView(R.id.tv_data_class)
    TextView tvDataClass;

    @BindView(R.id.tv_time_search)
    TextView tvTimeSearch;

    @BindView(R.id.ll_time)
    LinearLayout llTime;

    private AlphaAnimation alphaAniShow, alphaAniHide;
    //数据类型
    PopupWindow popupWindow;
    private Integer firstChoose;//0为人工摄影；1为无人机摄影

    //时间范围
    PopupWindow popupWindow1;
    PopupWindow popupWindow2;
    TimePickerView timePickerView;

    //
    @Override
    public int setContentViewResId() {
        return R.layout.fragment_spacetime;
    }

    @Override
    public void initView() {
        testData();
        alphaAnimation();
        rvFlaw.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        baseQuickAdapter = new BaseQuickAdapter<FlawRecord, BaseViewHolder>(R.layout.item_flaw_record,flawRecords) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, FlawRecord flawRecord) {

            }
        };
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                Intent intent = new Intent(getActivity(), FlawListActivity.class);
                startActivity(intent);
            }
        });
        rvFlaw.setAdapter(baseQuickAdapter);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_data_class_search, null);
        TextView tvArtificial = contentView.findViewById(R.id.tv_artificial);
        TextView tvDrone = contentView.findViewById(R.id.tv_drone);
        Button btConfirm = contentView.findViewById(R.id.bt_confirm);
        popupWindow = new PopupWindow(contentView);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tvDataClass.setTextColor(getResources().getColor(R.color.black));
            }
        });
        tvArtificial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstChoose = 0;
                tvArtificial.setBackground(getResources().getDrawable(R.color.grey));
                tvDrone.setBackground(getResources().getDrawable(R.color.white));
            }
        });
        tvDrone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstChoose = 1;
                tvArtificial.setBackground(getResources().getDrawable(R.color.white));
                tvDrone.setBackground(getResources().getDrawable(R.color.grey));
            }
        });

        llData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDataClass.setTextColor(getResources().getColor(R.color.blue_shallow));
                popupWindow.setWidth(llSearchBar.getWidth());
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.showAsDropDown(llSearchBar);
                popupWindow.setAnimationStyle(R.style.windowAnimation);

            }
        });
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (firstChoose == null){
                    tvDataClass.setText("数据类型");
                }else if (firstChoose == 0){
                    tvDataClass.setText("人工摄影");
                }else if (firstChoose == 1){
                    tvDataClass.setText("无人机摄影");
                }
            }
        });
        initTimeSearchPopWindow();
    }

    private void initTimeSearchPopWindow(){
        View contentView1 = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_time_choose, null);
        LinearLayout llStart = contentView1.findViewById(R.id.ll_time_start);
        LinearLayout llEnd = contentView1.findViewById(R.id.ll_time_end);
        Button btConfirm1 = contentView1.findViewById(R.id.bt_confirm);

        popupWindow1 = new PopupWindow(contentView1);
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tvTimeSearch.setTextColor(getResources().getColor(R.color.black));
            }
        });
        llTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTimeSearch.setTextColor(getResources().getColor(R.color.blue_shallow));
                popupWindow1.setWidth(llSearchBar.getWidth());
                popupWindow1.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//                popupWindow1.setOutsideTouchable(true);
                popupWindow1.showAsDropDown(llSearchBar);
                popupWindow1.setAnimationStyle(R.style.windowAnimation);
            }
        });
        btConfirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
                if (timePickerView != null){
                    timePickerView.dismiss();
                }
            }
        });
        llStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });
        llEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });
    }

    private void showDateTimePicker(){
        //时间选择器
        timePickerView = new TimePickerView(getActivity(), TimePickerView.Type.ALL);
        timePickerView.setTime(new Date());
        timePickerView.setCyclic(false);
        timePickerView.setCancelable(true);
        timePickerView.show();
    }

    private void testData(){
        for (int i = 0; i < 20; i++) {
            FlawRecord flawRecord = new FlawRecord();
            flawRecord.setName("缺陷名称");
            flawRecord.setTime("2022-8-19");
            flawRecords.add(flawRecord);
        }
    }

    /**
     * 改变背景颜色
     */
    private void darkenBackground(Float bgcolor){
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgcolor;

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);

    }

    //透明度动画
    private void alphaAnimation() {
        //显示
        alphaAniShow = new AlphaAnimation(0, 1);//百分比透明度，从0%到100%显示
        alphaAniShow.setDuration(500);
        //隐藏
        alphaAniHide = new AlphaAnimation(1, 0);
        alphaAniHide.setDuration(500);
    }


    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
