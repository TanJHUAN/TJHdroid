package com.example.tjhdroid.tileprovider;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

public interface IRegisterReceiver {

    Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter);

    void unregisterReceiver(BroadcastReceiver receiver);

    void destroy();
}
