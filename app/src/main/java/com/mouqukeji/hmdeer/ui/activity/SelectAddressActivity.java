package com.mouqukeji.hmdeer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseMapActivity;
import com.mouqukeji.hmdeer.contract.activity.SelectBuyAddressContract;
import com.mouqukeji.hmdeer.modle.activity.SelectBuyAddressModel;
import com.mouqukeji.hmdeer.presenter.activity.SelectBuyAddressPresenter;
import com.mouqukeji.hmdeer.ui.adapter.SelectAddressRecyclerviewAdapter;
import com.mouqukeji.hmdeer.ui.fragment.SelectCityFragment;
import com.mouqukeji.hmdeer.util.EventMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SelectAddressActivity extends BaseMapActivity<SelectBuyAddressPresenter, SelectBuyAddressModel> implements SelectBuyAddressContract.View,
        View.OnClickListener, Inputtips.InputtipsListener, TextView.OnEditorActionListener, PoiSearch.OnPoiSearchListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.select_location)
    LinearLayout selectLocation;
    @BindView(R.id.select_search_et)
    EditText selectSearchEt;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.select_cancal)
    TextView selectCancal;
    @BindView(R.id.select_recyclerview)
    RecyclerView selectRecyclerview;
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.select_city)
    TextView selectCity;
    private PoiSearch poiSearch;
    private String city="杭州市";
    private Marker marker;
    private String lat;
    private String lon;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_select_address;
    }

    @Override
    protected void setUpView() {
        //设置title
        actionTitle.setText("选择地址");
        //设置点击事件监听
        initListener();
        //显示保存按钮
        actionSave.setVisibility(View.VISIBLE);
        //软键盘 查找按钮
        initSearchListener();
    }

    private void setRecyclerview(final List<Tip> list) {
        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        selectRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        SelectAddressRecyclerviewAdapter selectAddressRecyclerviewAdapter = new SelectAddressRecyclerviewAdapter(R.layout.adapter_select_address_layout, list);
        selectRecyclerview.setAdapter(selectAddressRecyclerviewAdapter);
        //recyclerview item 点击事件
        selectAddressRecyclerviewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                selectSearchEt.setText(list.get(i).getName());
                selectRecyclerview.setVisibility(View.GONE);
                searchLocation(selectSearchEt.getText().toString());//查询位置
            }
        });
    }

    //设置marker
    private void setMarker(ArrayList<PoiItem> list) {
        if (marker != null) {
            marker.remove();
        }
        //便于将一屏显示所有显示的点
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度
        //将所有搜寻到的点 显示在地图上
        marker = getAmap().addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(new LatLng(list.get(0).getLatLonPoint().getLatitude(), list.get(0).getLatLonPoint().getLongitude()))
                .draggable(true));
        boundsBuilder.include(marker.getPosition());//把所有点都include进去（LatLng类型）

        getAmap().animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 15));//第二个参数为四周留空宽度
    }

    private void initSearchListener() {
        selectSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    selectRecyclerview.setVisibility(View.VISIBLE);
                    //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                    InputtipsQuery inputquery = new InputtipsQuery(s.toString(), city);
                    inputquery.setCityLimit(true);//限制在当前城市
                    Inputtips inputTips = new Inputtips(SelectAddressActivity.this, inputquery);
                    inputTips.setInputtipsListener(SelectAddressActivity.this);
                    inputTips.requestInputtipsAsyn();
                } else {
                    selectRecyclerview.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        selectRecyclerview.setVisibility(View.VISIBLE);//显示列表

    }

    private void initListener() {
        selectLocation.setOnClickListener(this);
        selectCancal.setOnClickListener(this);
        selectSearchEt.setOnEditorActionListener(this);
        actionSave.setOnClickListener(this);
        actionBack.setOnClickListener(this);
    }


    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_location://选择市
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new SelectCityFragment(), "city").commit();
                break;
            case R.id.select_cancal://取消
                selectSearchEt.setText("");
                break;
            case R.id.action_save://保存
                Intent intent = new Intent();
                if (!TextUtils.isEmpty(selectSearchEt.getText().toString())) {
                    intent.putExtra("select_address", selectSearchEt.getText().toString());
                    intent.putExtra("select_point_lat", lat);
                    intent.putExtra("select_point_lon", lon);
                }else{
                    intent.putExtra("select_address", "");
                    intent.putExtra("select_point_lat", "");
                    intent.putExtra("select_point_lon", "");
                }
                setResult(RESULT_OK, intent);
                //关闭Activity
                finish();
                break;
            case R.id.action_back:
                Intent intent1 = new Intent();
                intent1.putExtra("select_address", "");
                intent1.putExtra("select_point_lat", "");
                intent1.putExtra("select_point_lon", "");
                setResult(RESULT_OK, intent1);
                finish();
                break;
        }
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        //通过tipList获取Tip信息
        if (list.size() != 0 && i == 1000) {
            //设置recyclerview
            setRecyclerview(list);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            //搜索内容不能为空
            if (TextUtils.isEmpty(v.getText().toString().trim())) {
                Toast.makeText(SelectAddressActivity.this, "搜索内容不能为空！", Toast.LENGTH_SHORT).show();
            } else {
                String trim = v.getText().toString().trim();
                searchLocation(trim);//查询位置
            }
            // 当按了搜索之后关闭软键盘
            ((InputMethodManager) search.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    SelectAddressActivity.this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            return true;
        }
        return false;
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    private void searchLocation(String s) {
        //查询搜索内容
        PoiSearch.Query query = new PoiSearch.Query(s, "", city);
        //设置查询
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        query.setCityLimit(true);
        // 设置查询页码
        query.setPageNum(0);
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() == 0) {
            city = aMapLocation.getCity();//获取当前城市
        } else {
            Log.e("AmapError", "location Error, ErrCode:"
                    + aMapLocation.getErrorCode() + ", errInfo:"
                    + aMapLocation.getErrorInfo());
        }
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        //解析result获取POI信息
        if (i == 1000) {
            ArrayList<PoiItem> pois = poiResult.getPois();
            lat = pois.get(0).getLatLonPoint().getLatitude()+"";
            lon = pois.get(0).getLatLonPoint().getLongitude()+"";
            //设置marker
            setMarker(pois);
        } else {
            Toast.makeText(this, "无法查询到当前位置", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            city = (String) event.getData();
            selectCity.setText(city);
        }
    }

    @Override
    public void onBackPressed() {
        setBack();
        super.onBackPressed();
    }

    private void setBack() {
        Intent intent = new Intent();
        intent.putExtra("select_address", "");
        intent.putExtra("select_point_lat", "");
        intent.putExtra("select_point_lon", "");
        setResult(RESULT_OK, intent);
        //关闭Activity
        finish();
    }

}
