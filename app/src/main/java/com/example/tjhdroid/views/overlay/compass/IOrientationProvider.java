package com.example.tjhdroid.views.overlay.compass;


public interface IOrientationProvider {
    boolean startOrientationProvider(IOrientationConsumer orientationConsumer);

    void stopOrientationProvider();

    float getLastKnownOrientation();

    void destroy();
}
