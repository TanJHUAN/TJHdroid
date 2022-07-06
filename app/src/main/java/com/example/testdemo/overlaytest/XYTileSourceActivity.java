package com.example.testdemo.overlaytest;

import android.content.Context;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tjhdroid.config.Configuration;
import com.example.tjhdroid.library.R;
import com.example.tjhdroid.tileprovider.tilesource.TileSourceFactory;
import com.example.tjhdroid.views.MapView;

public class XYTileSourceActivity extends AppCompatActivity {

    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xytile_source);
        initView();
    }

    private void initView(){
        mapView = findViewById(R.id.map_source);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        /**
         * MAPNIK地图源
         */
//        mapView.setTileSource(TileSourceFactory.MAPNIK);
//        mapView.setTileSource(TileSourceFactory.WIKIMEDIA);
        mapView.setTileSource(TileSourceFactory.FIETS_OVERLAY_NL);
    }
}