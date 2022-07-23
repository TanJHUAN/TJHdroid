package com.example.testdemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import androidx.annotation.RequiresApi;

public class NetworkUtils {
    /**
     * 判断网络是否连接
     *
     * @param ctxt
     * @return true :连接 ； false: 断开
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static boolean isConnected(Context ctxt) {
        ConnectivityManager cm = (ConnectivityManager) ctxt.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities networkCapabilities = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            networkCapabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
        }

        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
    }

}
