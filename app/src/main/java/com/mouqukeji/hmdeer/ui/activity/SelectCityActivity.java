package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.UserEntity;
import com.mouqukeji.hmdeer.ui.adapter.BannerHeaderAdapter;
import com.mouqukeji.hmdeer.ui.adapter.ContactAdapter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

public class SelectCityActivity extends BaseActivity {
    @BindView(R.id.pic_contact_back)
    ImageView picContactBack;
    @BindView(R.id.indexableLayout)
    IndexableLayout indexableLayout;
    @BindView(R.id.select_city_actionbar)
    MyActionBar selectCityActionbar;
    private ContactAdapter mAdapter;
    private String city;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_selectcity;
    }

    @Override
    protected void setUpView() {
        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        onlisten();
    }

    public void initAdapter() {
        mAdapter = new ContactAdapter(this);
        indexableLayout.setAdapter(mAdapter);
        //设置字母提示框为仿os居中
        indexableLayout.setOverlayStyle_Center();
        mAdapter.setDatas(initDatas());
        // 全字母排序。  排序规则设置为：每个字母都会进行比较排序；速度较慢
        indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);
        // 这里BannerView只有一个Item, 添加一个长度为1的任意List作为第三个参数
        List<String> bannerList = new ArrayList<>();
        bannerList.add("");
        BannerHeaderAdapter mBannerHeaderAdapter = new BannerHeaderAdapter(this, "↑", null, bannerList, city);
        indexableLayout.addHeaderAdapter(mBannerHeaderAdapter);

        mBannerHeaderAdapter.setOnItemClickListener(new BannerHeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, List list) {
                //item点击事件
                setBack(list.get(position).toString());
            }
        });
    }

    private void setBack(String s) {
        Intent intent = new Intent();
        intent.putExtra("locationCity", s);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onlisten() {

        picContactBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回
                setBack("");
            }
        });

        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<UserEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, UserEntity entity) {
                if (originalPosition >= 0) {
                    //发送消息
                    setBack(entity.getNick());
                } else {
                    Toast.makeText(SelectCityActivity.this, "选中Header/Footer:" + entity.getNick() + "当前位置:" + currentPosition, Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private List<UserEntity> initDatas() {
        List<UserEntity> list = new ArrayList<>();
        // 初始化数据，R.array.provinces是城市资源，下面有贴出资源文件代码
        List<String> contactStrings = Arrays.asList(this.getResources().getStringArray(R.array.provinces));
        List<String> mobileStrings = Arrays.asList(this.getResources().getStringArray(R.array.provinces));
        for (int i = 0; i < contactStrings.size(); i++) {
            UserEntity contactEntity = new UserEntity(contactStrings.get(i), mobileStrings.get(i));
            list.add(contactEntity);
        }
        return list;
    }

    @Override
    protected void setUpData() {

    }
}
