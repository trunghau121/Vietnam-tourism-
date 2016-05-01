package com.example.hau.dulichviet.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.hau.dulichviet.interfaces.EventNetWork;
import com.example.hau.dulichviet.network.Networking;

/**
 * Created by HAU on 12/5/2015.
 */
public class ListenNetWork extends BroadcastReceiver {
    private EventNetWork netWork;
    private Context mContext;

    public ListenNetWork(EventNetWork netWork, Context mContext) {
        this.netWork = netWork;
        this.mContext = mContext;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals(ConnectivityManager
                .CONNECTIVITY_ACTION)) {

            boolean isConnected = Networking.isCheckConnect(mContext);

            if (isConnected) {
                netWork.EnableNetWork(1);
            } else {
                netWork.EnableNetWork(0);
            }
        }
    }
}

