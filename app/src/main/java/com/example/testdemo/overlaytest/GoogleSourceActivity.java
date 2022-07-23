package com.example.testdemo.overlaytest;

import android.os.Build;
import android.os.Environment;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.testdemo.utils.NetworkUtils;
import com.example.tjhdroid.library.R;
import com.example.tjhdroid.tileprovider.modules.ArchiveFileFactory;
import com.example.tjhdroid.tileprovider.modules.IArchiveFile;
import com.example.tjhdroid.tileprovider.modules.OfflineTileProvider;
import com.example.tjhdroid.tileprovider.tilesource.FileBasedTileSource;
import com.example.tjhdroid.tileprovider.tilesource.GoogleTileSource;
import com.example.tjhdroid.tileprovider.tilesource.TileSourceFactory;
import com.example.tjhdroid.tileprovider.utils.SimpleRegisterReceiver;
import com.example.tjhdroid.views.MapView;

import java.io.File;
import java.util.Objects;
import java.util.Set;

public class GoogleSourceActivity extends AppCompatActivity {

    private MapView googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_source);
        initView();
    }

    private void initView(){
        googleMap = findViewById(R.id.google_map);
        googleMap.setTileSource(GoogleTileSource.GoogleSat);
        googleMap.setBuiltInZoomControls(true);
        googleMap.setMultiTouchControls(true);
    }

    /**
     * 设置地图源
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setMapSources() {
        if (NetworkUtils.isConnected(this)) {
            googleMap.setTileSource(GoogleTileSource.GoogleSat);
        } else {
            Toast.makeText(this, "当前无网络，请选择离线地图包", Toast.LENGTH_SHORT).show();
        if (googleMap.getOverlays().size() <= 0) {
            mapViewOffline();
        }
    }
}
    /**
     * 加载离线地图
     */
    public void mapViewOffline() {
        String strFilepath = Environment.getExternalStorageDirectory().getPath() + "/osmdroid/jiang.sqlite";
        File exitFile = new File(strFilepath);
        String fileName = exitFile.getName();
        if (!exitFile.exists()) {
            googleMap.setTileSource(TileSourceFactory.MAPNIK);
        } else {
            fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (ArchiveFileFactory.isFileExtensionRegistered(fileName)) {
                try {
                    OfflineTileProvider tileProvider = new OfflineTileProvider(new SimpleRegisterReceiver(this), new File[]{exitFile});
                    googleMap.setTileProvider(tileProvider);

                    String source = "";
                    IArchiveFile[] archives = tileProvider.getArchives();
                    if (archives.length > 0) {
                        Set<String> tileSources = archives[0].getTileSources();
                        if (!tileSources.isEmpty()) {
                            source = tileSources.iterator().next();
                            googleMap.setTileSource(FileBasedTileSource.getSource(source));
                        } else {
                            googleMap.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
                        }

                    } else
                        googleMap.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
                    Toast.makeText(this, "Using " + exitFile.getAbsolutePath() + " " + source, Toast.LENGTH_LONG).show();
                    googleMap.invalidate();
                    return;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Toast.makeText(this, " did not have any files I can open! Try using MOBAC", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, " dir not found!", Toast.LENGTH_LONG).show();
            }
        }
    }


}