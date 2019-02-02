package com.mouqukeji.hmdeer.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;

import butterknife.BindView;

public class AboutActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.about_actionbar)
    MyActionBar aboutActionbar;
    @BindView(R.id.about_versiom)
    LinearLayout aboutVersiom;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_about;
    }

    @Override
    protected void setUpView() {
        setListener();
        //设置title
        aboutActionbar.setTitle("关于宅鹿");
    }

    private void setListener() {
        aboutVersiom.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.about_versiom:
                Toast.makeText(AboutActivity.this,"当前已是最新版",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
