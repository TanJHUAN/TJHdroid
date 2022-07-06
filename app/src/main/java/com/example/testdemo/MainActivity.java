package com.example.testdemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.tjhdroid.api.IMapController;
import com.example.tjhdroid.config.Configuration;
import com.example.tjhdroid.library.R;
import com.example.tjhdroid.tileprovider.tilesource.TileSourceFactory;
import com.example.tjhdroid.utils.GeoPoint;
import com.example.tjhdroid.views.MapView;
import com.example.tjhdroid.views.overlay.*;
import com.example.tjhdroid.views.overlay.compass.CompassOverlay;
import com.example.tjhdroid.views.overlay.compass.InternalCompassOrientationProvider;
import com.example.tjhdroid.views.overlay.gestures.RotationGestureOverlay;
import com.example.tjhdroid.views.overlay.gridlines.LatLonGridlineOverlay2;
import com.example.tjhdroid.views.overlay.mylocation.GpsMyLocationProvider;
import com.example.tjhdroid.views.overlay.mylocation.MyLocationNewOverlay;
import com.example.tjhdroid.views.overlay.mylocation.SimpleLocationOverlay;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;

    private MinimapOverlay mMinimapOverlay;

    private MyLocationNewOverlay mMyLocationOverlay;

    private CompassOverlay compassOverlay;

    private RotationGestureOverlay mRotationGestureOverlay;

    private ScaleBarOverlay mScaleBarOverlay;

    ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();

    List<GeoPoint> geoPoints = new ArrayList<GeoPoint>();

    private String permissions[] = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.tjh_map);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        requestPermissionsIfNecessary(permissions);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        //设置中心点
        IMapController mapController = mapView.getController();
        mapController.setZoom(14);
        GeoPoint centerPoint = new GeoPoint(39.901873, 116.326655);
        mapController.setCenter(centerPoint);


        final DisplayMetrics dm = getResources().getDisplayMetrics();

        //小地图
        mMinimapOverlay = new MinimapOverlay(ctx, mapView.getTileRequestCompleteHandler());
        mMinimapOverlay.setWidth(dm.widthPixels / 5);
        mMinimapOverlay.setHeight(dm.heightPixels / 5);
        mapView.getOverlays().add(this.mMinimapOverlay);
        //定位
        mMyLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this),mapView);
        mMyLocationOverlay.enableMyLocation();
        mapView.getOverlays().add(mMyLocationOverlay);
        //指南针
        compassOverlay = new CompassOverlay(this, new InternalCompassOrientationProvider(this), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);
        //网格

        LatLonGridlineOverlay2 gridOverlay = new LatLonGridlineOverlay2();
        mapView.getOverlays().add(gridOverlay);

        //旋转按钮
        mRotationGestureOverlay = new RotationGestureOverlay(this, mapView);
        mRotationGestureOverlay.setEnabled(true);
        mapView.setMultiTouchControls(true);
        mapView.getOverlays().add(mRotationGestureOverlay);

        //比例尺
        mScaleBarOverlay = new ScaleBarOverlay(mapView);
        mScaleBarOverlay.setCentred(true);
        //play around with these values to get the location on screen in the right place for your application
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mapView.getOverlays().add(this.mScaleBarOverlay);

        //在地图上加点
        Drawable drawable = getResources().getDrawable(R.drawable.ic_marker);
        OverlayItem item = new OverlayItem("Title", "Description",centerPoint);
        item.setMarker(drawable);
        items.add(item);
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return false;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        }, this);
        mOverlay.setFocusItemsOnTap(true);
        mapView.getOverlays().add(mOverlay);
        //PathOverlay 路线Overlay
        GeoPoint gp1 = new GeoPoint(40.067225, 116.369758);
        GeoPoint gp2 = new GeoPoint(40.064808, 116.346362);
        GeoPoint gp3 = new GeoPoint(40.058669, 116.336648);
        GeoPoint gp4 = new GeoPoint(40.036685, 116.343619);
        GeoPoint gp5 = new GeoPoint(40.036368, 116.327699);
        geoPoints.add(gp1);
        geoPoints.add(gp2);
        geoPoints.add(gp3);
        geoPoints.add(gp4);
        geoPoints.add(gp5);
        Polyline polyline = new Polyline();
        polyline.setWidth(6);
        polyline.setColor(0xFF1B7BCD);
        polyline.setPoints(geoPoints);
        mapView.getOverlays().add(polyline);
        mapController.setCenter(gp1);
        //Simple图层
        SimpleLocationOverlay simpleLocation = new SimpleLocationOverlay(this);
        simpleLocation.setEnabled(true);
        simpleLocation.setLocation(gp5);
        mapView.getOverlays().add(simpleLocation);
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }
}