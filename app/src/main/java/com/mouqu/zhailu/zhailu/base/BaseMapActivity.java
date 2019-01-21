package com.mouqu.zhailu.zhailu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.bean.ErrorBean;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;
import com.mouqu.zhailu.zhailu.util.ClassReflectHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMapActivity<P extends BasePresenter, M extends BaseModel> extends FragmentActivity implements IBaseView,AMapLocationListener {
    private Unbinder mUnbinder;
    protected P mMvpPresenter;
    protected M mModel;
    protected MultipleStatusView mMultipleStateView;
    private MapView map;
    private AMap aMap;


    /** 声明mlocationClient对象 */
    private AMapLocationClient mlocationClient = null;

    /** 声明mLocationOption对象 */
    private AMapLocationClientOption mLocationOption = null;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setLayoutResourceID() != 0) {
            mMultipleStateView = new MultipleStatusView(this);
            setContentView(View.inflate(this, setLayoutResourceID(), mMultipleStateView));
            mUnbinder = ButterKnife.bind(this);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }

        map = findViewById(R.id.map);

        map.onCreate(savedInstanceState);
        aMap = map.getMap();
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(false);

        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        //如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会。
        mLocationOption.setOnceLocationLatest(true);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        //启动定位
        mlocationClient.startLocation();



        //MVP
        mMvpPresenter = ClassReflectHelper.getT(this, 0);
        mModel = ClassReflectHelper.getT(this, 1);
        if (this instanceof IBaseView) {
            if (mMvpPresenter != null && mModel != null) {
                mMvpPresenter.setVM(this, mModel);
            }
        }
        initViewAndEvents();


        //actionbar 设置
        initActionBar();
        setUpView();
        setUpData();

    }

    public AMap getAmap() {
        return aMap;
    }

    protected abstract void initViewAndEvents();


    public void initActionBar() {
        View action_back = findViewById(R.id.action_back);
        action_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    protected abstract int setLayoutResourceID();


    //绑定view
    protected abstract void setUpView();

    //绑定数据
    protected abstract void setUpData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mlocationClient.unRegisterLocationListener(this);
        mlocationClient = null;
    }

    @Override
    public void showLoading(MultipleStatusView multipleStatusView, String msg) {

    }


    @Override
    public void hideLoading() {

    }

    @Override
    public void showBusinessError(ErrorBean error) {
        mMultipleStateView.showError();
    }

    @Override
    public void showException(ErrorBean error) {
        mMultipleStateView.showNoNetwork();
    }

    /**
     * 高德定位回调
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            Double latitude = amapLocation.getLatitude();
            Double longitude = amapLocation.getLongitude();
        }
    }




}
