package com.example.testdemo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.testdemo.overlaytest.GoogleSourceActivity;
import com.example.testdemo.overlaytest.XYTileSourceActivity;
import com.example.tjhdroid.library.R;
import com.example.tjhdroid.views.MapView;

public class HomeActivity extends AppCompatActivity {

    Button bt_source;
    Button bt_main;
    Button bt_google;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView(){
        bt_source = findViewById(R.id.bt_source);
        bt_main = findViewById(R.id.bt_main);
        bt_google = findViewById(R.id.bt_google);
        bt_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bt_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, XYTileSourceActivity.class);
                startActivity(intent);
            }
        });

        bt_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, GoogleSourceActivity.class);
                startActivity(intent);
            }
        });
    }


}