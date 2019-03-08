package com.mouqukeji.hmdeer.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;

import org.w3c.dom.Text;

import java.util.List;


public class DialogProcressRecyclerviewAdapter extends RecyclerView.Adapter<DialogProcressRecyclerviewAdapter.MyViewHolder> {
    private final String progress;
    private final List list;
    private final List listTime;
    private Context context;

    public DialogProcressRecyclerviewAdapter(Context context, String progress, List list, List listTime) {
        this.context = context;
        this.progress = progress;
        this.list = list;
        this.listTime = listTime;
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
        if (i == list.size() - 1) {
            viewHolder.line.setVisibility(View.GONE);
        }

        if (progress.equals("1")) {
            viewHolder.title.setText(list.get(i).toString());
            if (i == 1) {
                viewHolder.time.setText("");
            } else {
                viewHolder.time.setText(listTime.get(i).toString());
            }
        } else if (progress.equals("2")) {
            viewHolder.title.setText(list.get(i).toString());
            if (i == 2) {
                viewHolder.time.setText("");
            } else {
                viewHolder.time.setText(listTime.get(i).toString());
            }
        } else if (progress.equals("3")) {
            viewHolder.title.setText(list.get(i).toString());
            viewHolder.time.setText(listTime.get(i).toString());
        } else if (progress.equals("5")) {
            viewHolder.title.setText(list.get(i).toString());
            if (i==5){
                viewHolder.time.setText("");
            }else{
                viewHolder.time.setText(listTime.get(i).toString());
            }
        } else if (progress.equals("4")) {
            viewHolder.title.setText(list.get(i).toString());
            viewHolder.time.setText(listTime.get(i).toString());
        } else if (progress.equals("6")) {
            viewHolder.title.setText(list.get(i).toString());
            viewHolder.time.setText(listTime.get(i).toString());
        } else if (progress.equals("7")) {
            viewHolder.title.setText(list.get(i).toString());
            if (i==3){
                viewHolder.time.setText("");
            }else{
                viewHolder.time.setText(listTime.get(i).toString());
            }
        } else if (progress.equals("8")) {
            viewHolder.title.setText(list.get(i).toString());
            viewHolder.time.setText(listTime.get(i).toString());
        } else if (progress.equals("9")) {
            viewHolder.title.setText(list.get(i).toString());
            if (i==4){
                viewHolder.time.setText("");
            }else{
                viewHolder.time.setText(listTime.get(i).toString());
            }
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        View line;
        TextView title;
        TextView time;

        public MyViewHolder(View view) {
            super(view);
            line = view.findViewById(R.id.adapter_dialog_procress_line);
            title = view.findViewById(R.id.adapter_title_tv);
            time = view.findViewById(R.id.adapter_time_tv);
        }
    }

}