package com.example.testdemo.activities;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.BindView;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.style.FontStyle;
import com.example.testdemo.pojo.AnalysisResult;
import com.example.tjhdroid.library.R;

import java.util.ArrayList;
import java.util.List;

public class AnalysisResultActivity extends BaseActivity {

    @BindView(R.id.table)
    SmartTable smartTable;

    List<AnalysisResult> analysisResults = new ArrayList<>();

    @Override
    protected int setContentViewResId() {
        return R.layout.activity_analysis_result;
    }

    @Override
    protected void initView() {
        analysisResults.add(new AnalysisResult("1", "裂纹", "4434"));
        analysisResults.add(new AnalysisResult("2", "钢筋", "0"));
        analysisResults.add(new AnalysisResult("3", "油漆", "0"));
        analysisResults.add(new AnalysisResult("4", "析钙", "65"));
        analysisResults.add(new AnalysisResult("5", "后期混凝土", "10269"));
        analysisResults.add(new AnalysisResult("6", "渗透", "16732"));
        smartTable.setData(analysisResults);
        smartTable.getConfig().setContentStyle(new FontStyle(30, Color.BLACK));
        smartTable.getConfig().setColumnTitleStyle(new FontStyle(35,Color.BLUE));
    }
}