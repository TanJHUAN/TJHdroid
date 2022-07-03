package com.example.tjhdroid.views.mylocation;

import android.location.Location;

public interface IMyLocationProvider {
    boolean startLocationProvider(IMyLocationConsumer myLocationConsumer);

    void stopLocationProvider();

    Location getLastKnownLocation();

    void destroy();
}
