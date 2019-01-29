package com.mouqukeji.hmdeer.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mouqukeji.hmdeer.R;


public class DialogProcressRecyclerviewAdapter extends RecyclerView.Adapter<DialogProcressRecyclerviewAdapter.MyViewHolder> {
    private Context context;

    public DialogProcressRecyclerviewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_dialog_procress_layout, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int i) {
        if (i==3){
            viewHolder.line.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }


    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        View line;
        public MyViewHolder(View view) {
            super(view);
            line=view.findViewById(R.id.adapter_dialog_procress_line);
        }
    }

}