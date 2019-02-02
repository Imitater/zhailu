package com.mouqukeji.hmdeer.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.LocationBean;
import com.mouqukeji.hmdeer.ui.adapter.SelectLocationRecyclerviewAdapter;


import java.util.ArrayList;

public class PoiSearchTask implements PoiSearch.OnPoiSearchListener {

    private static PoiSearchTask mInstance;
    private PoiSearch mSearch;
    private Context mContext;
    private ArrayList<LocationBean> datas;
    private RecyclerView recyclerview;

    private PoiSearchTask(Context context) {
        this.mContext = context;
    }

    public static PoiSearchTask getInstance(Context context) {
        if (mInstance == null) {
            synchronized (PoiSearchTask.class) {
                if (mInstance == null) {
                    mInstance = new PoiSearchTask(context);
                }
            }
        }
        return mInstance;
    }

    public void onSearch(String key, String city, double lat, double lng) {
        // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        PoiSearch.Query query = new PoiSearch.Query(key, "", city);
        mSearch = new PoiSearch(mContext, query);
        mSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(lat, lng), 2000));//设置周边搜索的中心点以及半径
        //设置异步监听
        mSearch.setOnPoiSearchListener(this);
        //查询POI异步接口
        mSearch.searchPOIAsyn();
    }

    public PoiSearchTask setRecyclerview(RecyclerView recyclerview) {
        this.recyclerview = recyclerview;
        return this;
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int rCode) {
        if (rCode == 1000) {
            if (poiResult != null && poiResult.getQuery() != null) {
                datas = new ArrayList<>();
                ArrayList<PoiItem> items = poiResult.getPois();
                for (PoiItem item : items) {
                    //获取经纬度对象
                    LatLonPoint llp = item.getLatLonPoint();
                    double lon = llp.getLongitude();
                    double lat = llp.getLatitude();
                    //获取标题
                    String title = item.getTitle();
                    //获取内容
                    String text = item.getSnippet();
                    datas.add(new LocationBean(lon, lat, title, text));
                }
                SelectLocationRecyclerviewAdapter selectAddressRecyclerviewAdapter = new SelectLocationRecyclerviewAdapter(R.layout.adapter_select_address_layout, datas);
                recyclerview.setAdapter(selectAddressRecyclerviewAdapter);
                //recyclerview item 点击事件
                selectAddressRecyclerviewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        if (mListener != null) {
                            mListener.onClick(i);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    public ArrayList<LocationBean> getDatas() {
        return datas;
    }

    public interface OnRecyclerItemClickListener {
        // 点击事件
        void onClick(int i);
    }

    private OnRecyclerItemClickListener mListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        mListener = listener;
    }
}

