package com.mouqukeji.hmdeer.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.LocationBean;
import com.mouqukeji.hmdeer.ui.adapter.SelectLocationEtRecyclerviewAdapter;


import java.util.ArrayList;
import java.util.List;

public class InputTipTask implements Inputtips.InputtipsListener {

    private static InputTipTask mInstance;
    private Inputtips mInputTips;
    private Context mContext;
    private List<LocationBean> dataLists = new ArrayList<>();
    private RecyclerView recyclerview;

    private InputTipTask(Context context) {
        this.mContext = context;
    }

    //单例模式
    public static InputTipTask getInstance(Context context) {
        if (mInstance == null) {
            synchronized (InputTipTask.class) {
                if (mInstance == null) {
                    mInstance = new InputTipTask(context);
                }
            }
        }
        return mInstance;
    }

    public InputTipTask setRecyclerview(RecyclerView recyclerview) {
        this.recyclerview=recyclerview;
         return this;
    }

    public List<LocationBean> getBean() {
        return dataLists;
    }

    public void searchTips(String keyWord, String city) {
        //第二个参数默认代表全国，也可以为城市区号
        InputtipsQuery inputquery = new InputtipsQuery(keyWord, city);
        inputquery.setCityLimit(true);
        mInputTips = new Inputtips(mContext, inputquery);
        mInputTips.setInputtipsListener(this);
        mInputTips.requestInputtipsAsyn();
    }

    @Override
    public void onGetInputtips(final List<Tip> tipList, int rCode) {
        if (rCode == 1000) {
            ArrayList<String> datas = new ArrayList<>();
            if(tipList != null){
                dataLists.clear();
                for(Tip tip:tipList){
                    datas.add(tip.getName());
                    dataLists.add(new LocationBean(tip.getPoint().getLongitude(),tip.getPoint().getLatitude(),tip.getName(),tip.getDistrict()));
                }
            }
            SelectLocationEtRecyclerviewAdapter selectAddressEtRecyclerviewAdapter = new SelectLocationEtRecyclerviewAdapter(R.layout.adapter_select_address_layout, dataLists);
            recyclerview.setAdapter(selectAddressEtRecyclerviewAdapter);
            //recyclerview item 点击事件
            selectAddressEtRecyclerviewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    if (mListener != null) {
                        mListener.onClick(i);
                    }
                }
            });
        }
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
