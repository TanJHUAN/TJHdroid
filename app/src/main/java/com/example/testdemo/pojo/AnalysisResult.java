package com.example.testdemo.pojo;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable
public class AnalysisResult {
    @SmartColumn(id = 0, name = "序号")
    private String id;
    @SmartColumn(id = 1, name = "名称")
    private String name;
    @SmartColumn(id = 2, name = "数值")
    private String account;

    public AnalysisResult(String id, String name, String account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }
}
