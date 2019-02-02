package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiSearch;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.LatLngEntity;
import com.mouqukeji.hmdeer.bean.LocationBean;
import com.mouqukeji.hmdeer.contract.activity.SelectLocationContract;
import com.mouqukeji.hmdeer.modle.activity.SelectLocationModel;
import com.mouqukeji.hmdeer.presenter.activity.SelectLocationPresenter;
import com.mouqukeji.hmdeer.util.GeoCoderUtil;
import com.mouqukeji.hmdeer.util.InputTipTask;
import com.mouqukeji.hmdeer.util.PoiSearchTask;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class SelectLocationActivity extends BaseActivity<SelectLocationPresenter, SelectLocationModel> implements SelectLocationContract.View,
        View.OnClickListener, AMapLocationListener, Inputtips.InputtipsListener, TextView.OnEditorActionListener, AMap.OnCameraChangeListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.select_location_city)
    TextView selectLocationCity;
    @BindView(R.id.select_location_et)
    EditText selectLocationEt;
    @BindView(R.id.select_location_dismiss)
    TextView selectLocationDismiss;
    @BindView(R.id.select_locationo_map)
    MapView selectLocationoMap;
    @BindView(R.id.select_location_recyclerview)
    RecyclerView selectLocationRecyclerview;
    @BindView(R.id.select_location_et_recyclerview)
    RecyclerView selectLocationEtRecyclerview;
    @BindView(R.id.select_location_framlayout)
    FrameLayout selectLocationFramlayout;
    private AMap aMap;
    private String city;
    private PoiSearch poiSearch;
    private LocationBean currentLoc;
    private ArrayList<LocationBean> datas;
    private AMapLocationClient mlocationClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectLocationoMap.onCreate(savedInstanceState);
    }

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_selectlocation;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("选择地址");
        city = "杭州";
        //定位初始化
        init();
        initListener();
        initRecyclerview();
        //查找设置
        initSearchListener();
        aMap.setOnCameraChangeListener(this); // 添加移动地图事件监听器
        //设置recyclerview item点击事件
        PoiSearchTask.getInstance(SelectLocationActivity.this).setOnRecyclerItemClickListener(new PoiSearchTask.OnRecyclerItemClickListener() {
            @Override
            public void onClick(int i) {
                double lat = PoiSearchTask.getInstance(SelectLocationActivity.this).getDatas().get(i).getLat();
                double lon = PoiSearchTask.getInstance(SelectLocationActivity.this).getDatas().get(i).getLon();
                String title = PoiSearchTask.getInstance(SelectLocationActivity.this).getDatas().get(i).getTitle();
                setBack(title, lat, lon);
            }
        });
        //查找列表recyclerview item点击事件
        InputTipTask.getInstance(SelectLocationActivity.this).setOnRecyclerItemClickListener(new InputTipTask.OnRecyclerItemClickListener() {
            @Override
            public void onClick(int i) {
                double lat = InputTipTask.getInstance(SelectLocationActivity.this).getBean().get(i).getLat();
                double lon = InputTipTask.getInstance(SelectLocationActivity.this).getBean().get(i).getLon();
                String title = InputTipTask.getInstance(SelectLocationActivity.this).getBean().get(i).getTitle();
                setBack(title, lat, lon);
            }
        });
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        selectLocationDismiss.setOnClickListener(this);
        selectLocationCity.setOnClickListener(this);
    }

    private void initRecyclerview() {
        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        selectLocationRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
    }

    private void init() {
        aMap = selectLocationoMap.getMap();
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(false);
        //设置缩放比例
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        initLoc();//设置定位
        aMap.setMyLocationEnabled(true);
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));
        myLocationStyle.showMyLocation(false);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_back:
                Intent intent = new Intent();
                intent.putExtra("select_address", "");
                intent.putExtra("select_point_lat", "");
                intent.putExtra("select_point_lon", "");
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.select_location_dismiss:
                selectLocationFramlayout.setVisibility(View.GONE);
                selectLocationEt.setText("");
                break;
            case R.id.select_location_city:
                Intent intent1 = new Intent(SelectLocationActivity.this, SelectCityActivity.class);
                intent1.putExtra("city",city);
                startActivityForResult(intent1, 2);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 2:
                    if (!TextUtils.isEmpty(data.getStringExtra("locationCity"))) {
                        //获取地址
                        String locationCity = data.getStringExtra("locationCity");
                        selectLocationCity.setText(locationCity);
                        city=locationCity;
                    }
                    break;
            }
        }
    }
    //定位
    private void initLoc() {
        //初始化定位
        mlocationClient = new AMapLocationClient(getApplicationContext());
        //初始化定位参数
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(SelectLocationActivity.this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为1000ms
        mLocationOption.setInterval(1000);
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

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() == 0) {
            //移动地图中心到当前的定位位置
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()), 17));
            //获取定位信息
            final double latitude = aMapLocation.getLatitude();
            final double longitude = aMapLocation.getLongitude();
            city = aMapLocation.getCity();
            //这里是定位完成之后开始poi的附近搜索，代码在后面
            PoiSearchTask.getInstance(SelectLocationActivity.this).setRecyclerview(selectLocationRecyclerview).onSearch("", city, latitude, longitude);
        }
    }

    private void initSearchListener() {
        selectLocationEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {//第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                    selectLocationFramlayout.setVisibility(View.VISIBLE);
                    //设置Recyclerview 列表
                    LinearLayoutManager layoutManager = new LinearLayoutManager(SelectLocationActivity.this);
                    //设置布局管理器
                    selectLocationEtRecyclerview.setLayoutManager(layoutManager);
                    //设置为垂直布局，这也是默认的
                    layoutManager.setOrientation(OrientationHelper.VERTICAL);
                    //高德地图的输入的自动提示，代码在后面
                    InputTipTask.getInstance(SelectLocationActivity.this).setRecyclerview(selectLocationEtRecyclerview).searchTips(s.toString(), city);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onGetInputtips(List<Tip> list, int i) {

    }

    private void setBack(String title, double lat, double lon) {
        Intent intent = new Intent();
        intent.putExtra("select_address", title);
        intent.putExtra("select_point_lat", lat+"");
        intent.putExtra("select_point_lon", lon+"");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(final CameraPosition cameraPosition) {
        LatLngEntity latLngEntity = new LatLngEntity(cameraPosition.target.latitude, cameraPosition.target.longitude);
        //地理反编码工具类，代码在后面
        GeoCoderUtil.getInstance(this).geoAddress(latLngEntity, new GeoCoderUtil.GeoCoderAddressListener() {
            @Override
            public void onAddressResult(String result) {
                if (!selectLocationEt.getText().toString().trim().equals("")) {
                    //输入地址后的点击搜索
                    currentLoc = new LocationBean(cameraPosition.target.longitude, cameraPosition.target.latitude, selectLocationEt.getText().toString().trim(), "");
                } else {
                    //拖动地图
                    currentLoc = new LocationBean(cameraPosition.target.longitude, cameraPosition.target.latitude, result, "");
                }
                //地图的中心点位置改变后都开始poi的附近搜索
                PoiSearchTask.getInstance(SelectLocationActivity.this).setRecyclerview(selectLocationRecyclerview).onSearch("", city, cameraPosition.target.latitude, cameraPosition.target.longitude);
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (!TextUtils.isEmpty(v.getText().toString())) {//第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
            selectLocationFramlayout.setVisibility(View.VISIBLE);
            //设置Recyclerview 列表
            LinearLayoutManager layoutManager = new LinearLayoutManager(SelectLocationActivity.this);
            //设置布局管理器
            selectLocationEtRecyclerview.setLayoutManager(layoutManager);
            //设置为垂直布局，这也是默认的
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
            //高德地图的输入的自动提示，代码在后面
            InputTipTask.getInstance(SelectLocationActivity.this).setRecyclerview(selectLocationEtRecyclerview).searchTips(v.getText().toString(), city);
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mlocationClient.stopLocation();//停止定位
        mlocationClient.unRegisterLocationListener(this);
        mlocationClient = null;
    }
}
