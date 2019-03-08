package com.mouqukeji.hmdeer.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.TokenBean;
import com.mouqukeji.hmdeer.contract.activity.SplashContract;
import com.mouqukeji.hmdeer.modle.activity.SplashModel;
import com.mouqukeji.hmdeer.presenter.activity.SplashPresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.AppConstants;
import com.mouqukeji.hmdeer.util.CityUtils;
import com.mouqukeji.hmdeer.util.DataSaveSP;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.LoginStaticData;
import com.mouqukeji.hmdeer.util.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.wangyuwei.particleview.ParticleView;

public class SplashActivity extends BaseActivity<SplashPresenter, SplashModel> implements SplashContract.View {

    private static final String TAG = "spac";

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.INTERNET
    };
    private static final int PERMISSON_REQUESTCODE = 0;
    @BindView(R.id.actionbar)
    MyActionBar actionbar;

    private boolean isNeedCheck = true;
    private String userId;
    private boolean flag = false;
    private boolean isDown = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CityUtils().getCity(getApplicationContext());
    }

    @Override
    protected void initViewAndEvents() {
    }


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setUpView() {
        boolean isFirstOpen = SpUtils.getBoolean(AppConstants.FIRST_OPEN, SplashActivity.this);
        if (isDown & isFirstOpen) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (flag) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 2000);
        }

    }


    @Override
    protected void setUpData() {

    }

    //----------以下动态获取权限---------
    @Override
    protected void onResume() {
        super.onResume();
        // 判断是否是第一次开启应用
        boolean isFirstOpen = SpUtils.getBoolean(AppConstants.FIRST_OPEN, this);
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen) {
            Intent intent = new Intent(this, AppIntroActivity.class);
            startActivity(intent);
            finish();
        } else {
            isLogin();//登录token验证
            if (isNeedCheck)
                checkPermissions(needPermissions);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        isLogin();//登录token验证
        isNeedCheck=true;
        checkPermissions(needPermissions);
    }

    /**
     * 检查权限
     *
     * @param
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        //获取权限列表
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList && needRequestPermissonList.size() > 0) {
            //list.toarray将集合转化为数组
            ActivityCompat.requestPermissions(this, needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]), PERMISSON_REQUESTCODE);
        } else {
            isDown = true;
            //发送消息
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (flag) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 3000);
        }
    }


    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        //for (循环变量类型 循环变量名称 : 要被遍历的对象)
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale(this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                isDown = false;
                return false;
            }
        }
        isDown = true;
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {      //没有授权
                isNeedCheck = false;
                showMissingPermissionDialog();              //显示提示信息
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置");
        builder.setMessage("请给与相应的权限");

        // 拒绝, 退出应用
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }


    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isDown) {
                this.finish();
                return true;
            } else {
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //用于根据文件是否存在判断是否处在登录状态
    //用户的token信息保存的文件名为
    public void isLogin() {
        GetSPData getSPData = new GetSPData();
        String token = getSPData.getSPToken(SplashActivity.this);
        userId = getSPData.getSPUserID(SplashActivity.this);
        String telephone = getSPData.getSPTelephone(SplashActivity.this);
        //验证,从sharedpreferences获取数据
        //验证,从静态变量获取数据
        Log.i(TAG, "isLogin: 登录验证sp " + token + " " + userId + " " + telephone);
        if (LoginStaticData.token.equals(token) && LoginStaticData.userId.equals(userId) & LoginStaticData.telephone.equals(telephone)) {
            //静态数据正确
        } else {
            //静态变量赋值
            if (token != null && userId != null & telephone != null) {
                LoginStaticData.token = token;
                LoginStaticData.userId = userId;
                LoginStaticData.telephone = telephone;
            }
            //验证一下静态信息
            Log.i(TAG, "isLogin: 登录验证静态数据(主页): " + LoginStaticData.token + " " + LoginStaticData.userId + " " + telephone);
        }
        if (token != null) {
            ChackToken(token, userId);
        } else {
            flag = false;
        }
    }

    private void ChackToken(String token, String userId) {
        mMvpPresenter.getToken(this, token, mMultipleStateView);
    }

    @Override
    public void getToken(TokenBean bean) {
        if (userId.equals(bean.getUser_id())) {
            flag = true;
        } else {
            DataSaveSP dataSaveSP = new DataSaveSP();
            boolean b = dataSaveSP.dataSave(userId, SplashActivity.this);
            LoginStaticData.userId = userId;
            flag = false;
        }

    }

    @Override
    public void restart() {
        flag = false;
    }

}
