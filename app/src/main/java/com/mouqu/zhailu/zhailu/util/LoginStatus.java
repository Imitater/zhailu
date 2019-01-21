package com.mouqu.zhailu.zhailu.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.mouqu.zhailu.zhailu.ui.activity.MainActivity;


public class LoginStatus {
    public static void loginStatus(Context context,String token,String userId,String telephone){
        if (context instanceof Activity){
            Activity activity= (Activity) context;
            //这里是进行数据SP持久化+页面跳转
            DataSaveSP dataSaveSP=new DataSaveSP();
            boolean b=dataSaveSP.dataSave(token,userId,telephone,activity);
            //注册完成之后,在存储完token和userId之后则表示登录成功,跳转到主页面
            if (b){
                activity.finish();
                Intent intent=new Intent(activity,MainActivity.class);
                activity.startActivity(intent);
            }else {
                Toast toast=Toast.makeText(activity,"",Toast.LENGTH_SHORT);
                toast.setText("系统错误,注册无效");
                toast.show();
            }
        }

    }
}
