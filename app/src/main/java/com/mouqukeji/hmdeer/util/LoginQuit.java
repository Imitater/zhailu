package com.mouqukeji.hmdeer.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.ContextThemeWrapper;

import com.mouqukeji.hmdeer.ui.activity.MainActivity;
import com.mouqukeji.hmdeer.ui.activity.SignInActivity;


public class LoginQuit {
    private static final String TAG = "LoginQuit";

    //type是跳转类型,0表示跳转登录页,1表示跳转主页,跳转方式都为栈内复用
    public void loginQuit(ContextWrapper contextWrapper, int type) {
        if (contextWrapper instanceof ContextThemeWrapper) {
            //两次向下转型
//            ContextThemeWrapper contextThemeWrapper= (ContextThemeWrapper) contextWrapper;
            Activity activity = (Activity) contextWrapper;
            //清除本地登录信息
            SharedPreferences sharedPreferences = activity.getSharedPreferences("zhailu", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            boolean b = editor.commit();
            Log.i(TAG, "loginQuit: 是否成功清空SP数据 " + b);
            //activity销毁
            activity.finish();
            //跳转到登录页面
            Intent intent = new Intent(activity, SignInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
        }
    }
}
