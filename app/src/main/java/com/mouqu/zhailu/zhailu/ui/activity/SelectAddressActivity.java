package com.mouqu.zhailu.zhailu.ui.activity;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseMapActivity;
import com.mouqu.zhailu.zhailu.contract.activity.SelectBuyAddressContract;
import com.mouqu.zhailu.zhailu.modle.activity.SelectBuyAddressModel;
import com.mouqu.zhailu.zhailu.presenter.activity.SelectBuyAddressPresenter;
import com.mouqu.zhailu.zhailu.ui.adapter.CodeRecyclerviewAdapter;
import com.mouqu.zhailu.zhailu.ui.adapter.SelectAddressRecyclerviewAdapter;


import java.util.List;

import butterknife.BindView;

public class SelectAddressActivity extends BaseMapActivity<SelectBuyAddressPresenter, SelectBuyAddressModel> implements SelectBuyAddressContract.View,
        View.OnClickListener,Inputtips.InputtipsListener,TextView.OnEditorActionListener {
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
    private String content;

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
        //地图设置
        setMap();
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
        SelectAddressRecyclerviewAdapter selectAddressRecyclerviewAdapter= new SelectAddressRecyclerviewAdapter (R.layout.adapter_select_address_layout, list);
        selectRecyclerview.setAdapter(selectAddressRecyclerviewAdapter);
        //recyclerview item 点击事件
        selectAddressRecyclerviewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                selectSearchEt.setText(list.get(i).toString());
            }
        });
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
                    Toast.makeText(SelectAddressActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
                    //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                    InputtipsQuery inputquery = new InputtipsQuery(s.toString(), "杭州");
                    inputquery.setCityLimit(true);//限制在当前城市
                    Inputtips inputTips = new Inputtips(SelectAddressActivity.this, inputquery);
                    inputTips.setInputtipsListener(SelectAddressActivity.this);
                    inputTips.requestInputtipsAsyn();
                }else{
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
     }

    private void setMap() {
        //初始化定位
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        //设置是否显示定位小蓝点，
        myLocationStyle.showMyLocation(true);
        getAmap().setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        getAmap().getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        getAmap().setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

       // getAmap().setOnMyLocationChangeListener();//实现 AMap.OnMyLocationChangeListener 监听器，通过如下回调方法获取经纬度信息：
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_location://选择市
                break;
            case R.id.select_cancal://取消

                break;
            case R.id.action_save://保存
                break;
        }
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        //通过tipList获取Tip信息
        if (list.size()!=0&&i==1000){
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
            }else {
                 //查询搜索内容

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

}
