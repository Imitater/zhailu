package com.mouqukeji.hmdeer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.ErrorBean;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;
import com.mouqukeji.hmdeer.util.ClassReflectHelper;
import com.mouqukeji.hmdeer.util.EventBusUtils;
import com.mouqukeji.hmdeer.util.EventMessage;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseMapActivity<P extends BasePresenter, M extends BaseModel> extends FragmentActivity implements IBaseView, AMapLocationListener {
    private Unbinder mUnbinder;
    protected P mMvpPresenter;
    protected M mModel;
    protected MultipleStatusView mMultipleStateView;
    private MapView map;
    private AMap aMap;
    /**
     * 声明mlocationClient对象
     */
    private AMapLocationClient mlocationClient = null;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setLayoutResourceID() != 0) {
            mMultipleStateView = new MultipleStatusView(this);
            setContentView(View.inflate(this, setLayoutResourceID(), mMultipleStateView));
            mUnbinder = ButterKnife.bind(this);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }

        //MVP
        mMvpPresenter = ClassReflectHelper.getT(this, 0);
        mModel = ClassReflectHelper.getT(this, 1);
        if (this instanceof IBaseView) {
            if (mMvpPresenter != null && mModel != null) {
                mMvpPresenter.setVM(this, mModel);
            }
        }

        initViewAndEvents();
        map = findViewById(R.id.map);

        map.onCreate(savedInstanceState);
        aMap = map.getMap();
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(false);
        // 是否显示定位按钮
        uiSettings.setMyLocationButtonEnabled(false);
        LatLng marker1 = new LatLng(39.90403, 116.407525);
        //设置中心点
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker1));
        //设置缩放比例
        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
        initLoc();//设置定位

        aMap.setMyLocationEnabled(true);
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动
        myLocationStyle.strokeColor(getResources().getColor(R.color.transparent));//设置定位蓝点精度圆圈的边框颜色的方法。
        myLocationStyle.radiusFillColor(getResources().getColor(R.color.transparent));//设置定位蓝点精度圆圈的填充颜色的方法。
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.mipmap_location_blue);
        myLocationStyle.myLocationIcon(bitmap);//设置定位蓝点的icon图标方法，需要用到BitmapDescriptor类对象作为参数。

        if (isRegisteredEventBus()) {
            EventBusUtils.register(this);
        }
        //actionbar 设置
        initActionBar();
        setUpView();
        setUpData();

    }

    //定位
    private void initLoc() {
        //初始化定位
        mlocationClient = new AMapLocationClient(getApplicationContext());
        //初始化定位参数
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
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
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }

    /**
     * 是否注册事件分发
     *
     * @return true 注册；false 不注册，默认不注册
     */
    protected boolean isRegisteredEventBus() {
        return false;
    }

    /**
     * 接收到分发的事件
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(EventMessage event) {
    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceiveStickyEvent(EventMessage event) {
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
        //注销 eventbus
        if (isRegisteredEventBus()) {
            EventBusUtils.unregister(this);
        }
        mlocationClient.stopLocation();//停止定位
        mlocationClient.unRegisterLocationListener(this);
        mlocationClient = null;
    }

    @Override
    public void showLoading(MultipleStatusView multipleStatusView, String msg) {

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

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


}
