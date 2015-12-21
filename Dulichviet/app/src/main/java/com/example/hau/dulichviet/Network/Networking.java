package com.example.hau.dulichviet.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by HAU on 12/5/2015.
 */
public class Networking {

    public static boolean isCheckConnect(Context mContext) {
        boolean status = false;
        try {
            ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null && info.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (info != null && info.getState() == NetworkInfo.State.CONNECTED) {
                    status = true;
                }
            }

        } catch (Exception e) {
            return false;
        }
        return status;
    }
}
