package com.mouqu.zhailu.zhailu.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mouqu.zhailu.zhailu.R;


public class MyActionBar  extends LinearLayout {

    private View back;
    private TextView title;
    private TextView edit;

    public MyActionBar(Context context) {
        super(context);
    }

    public MyActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init() {
        View inflate = inflate(getContext(), R.layout.actionbar_dft, this);
        back = inflate.findViewById(R.id.action_back);
        title = inflate.findViewById(R.id.action_title);
        edit = inflate.findViewById(R.id.action_edit);
    }
    //设置按钮是否显示
    public void setEditShow(boolean flag){
        if (flag){
            edit.setVisibility(VISIBLE);
        }else{
            edit.setVisibility(GONE);
        }
    }
    //设置按钮title
    public void setEditTitle(String title){
        if (!TextUtils.isEmpty(title)) {
            edit.setText(title);
        }
    }
    //设置title
    public void setTitle(String strTitle){
        if (!TextUtils.isEmpty(strTitle)) {
            title.setText(strTitle);
            title.setVisibility(View.VISIBLE);
        } else {
            title.setVisibility(View.GONE);
        }
    }



}
