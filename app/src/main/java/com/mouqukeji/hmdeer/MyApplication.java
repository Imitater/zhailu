package com.mouqukeji.hmdeer;

import android.app.Application;
import android.content.Context;

import com.tencent.mm.opensdk.openapi.IWXAPI;


public class MyApplication extends Application {
    private static Context mContext;
    private static IWXAPI iwxapi;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
     }
    public static Context getContext() {
        return mContext;
    }
 }
