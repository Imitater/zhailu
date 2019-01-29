package com.mouqukeji.hmdeer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.ui.widget.QGridView;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.indexablerv.IndexableHeaderAdapter;

public class BannerHeaderAdapter extends IndexableHeaderAdapter implements AdapterView.OnItemClickListener {
    private static final int TYPE = 1;
    private final Context context;
    private List<String> list;
    private CYBChangeCityGridViewAdapter cybChangeCityGridViewAdapter;
    private String[] city = {"东莞", "深圳", "广州", "温州", "郑州", "金华", "佛山", "上海", "苏州", "杭州", "长沙", "中山"};
    private OnItemClickListener mOnItemClickListener = null;

    public BannerHeaderAdapter(Context context, String index, String indexTitle, List datas) {
        super(index, indexTitle, datas);
        this.context = context;
    }

    @Override
    public int getItemViewType() {
        return TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_city_header, parent, false);
        VH holder = new VH(view);
        return holder;
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, Object entity) {
        // 数据源为null时, 该方法不用实现
        VH vh = (VH) holder;
        list = new ArrayList<String>();
        for (int i = 0; i < city.length; i++) {
            list.add(city[i]);
        }
        cybChangeCityGridViewAdapter = new CYBChangeCityGridViewAdapter(context, list);
        // 绑定adpter
        vh.head_home_change_city_gridview.setAdapter(cybChangeCityGridViewAdapter);
        //热门城市的item点击事件
        vh.head_home_change_city_gridview.setOnItemClickListener(this);
}


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view, position , list);
        }
    }

    private class VH extends RecyclerView.ViewHolder {
    GridView head_home_change_city_gridview;
    TextView item_header_city_dw;

    public VH(View itemView) {
        super(itemView);
        head_home_change_city_gridview = (QGridView) itemView.findViewById(R.id.item_header_city_gridview);
        item_header_city_dw = (TextView) itemView.findViewById(R.id.item_header_city_dw);
    }
}

public static interface OnItemClickListener {
    void onItemClick(View view, int position, List list);

}

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


}


