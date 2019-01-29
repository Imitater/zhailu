package com.mouqukeji.hmdeer.ui.activity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.TokenBean;
import com.mouqukeji.hmdeer.contract.activity.MainContract;
import com.mouqukeji.hmdeer.modle.activity.MainModel;
import com.mouqukeji.hmdeer.presenter.activity.MainPresenter;
import com.mouqukeji.hmdeer.ui.adapter.MainPageAdapter;
import com.mouqukeji.hmdeer.ui.fragment.IndentFragment;
import com.mouqukeji.hmdeer.ui.fragment.IndexFragment;
import com.mouqukeji.hmdeer.ui.fragment.MyFragment;
import com.mouqukeji.hmdeer.ui.widget.CustomViewPager;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.DataSaveSP;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.LoginStaticData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


//主页面activity
public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View, View.OnClickListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.main_order_iv)
    ImageView mainOrderIv;
    @BindView(R.id.main_my_iv)
    ImageView mainMyIv;
    private String TAG = "MainActivity";
    @BindView(R.id.main_viewpager)
    CustomViewPager mainViewpager;
    @BindView(R.id.main_order)
    LinearLayout mainOrder;
    @BindView(R.id.main_index)
    LinearLayout mainIndex;
    @BindView(R.id.main_my)
    LinearLayout mainMy;
    @BindView(R.id.main_a_layout)
    RelativeLayout mainALayout;
    @BindView(R.id.main_index_iv)
    ImageView mainIndexIv;
    @BindView(R.id.main_actionbar)
    MyActionBar mainActionbar;
    private List<Fragment> list = new ArrayList();
    private String userId;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        //fragment 页面加载
        initFragment();
        //设置viewpager
        mainViewpager.setAdapter(new MainPageAdapter(getSupportFragmentManager(), list));
        mainViewpager.setOffscreenPageLimit(3);//ViewPager设置预加载页面的个数方法
        //默认选择首页
        mainViewpager.setCurrentItem(1);
        //设置点击事件
        initListener();
    }


    private void initFragment() {
        list.add(new IndentFragment());
        list.add(new IndexFragment());
        list.add(new MyFragment());
    }

    private void initListener() {
        mainOrder.setOnClickListener(this);
        mainIndex.setOnClickListener(this);
        mainMy.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i) {
            case 0://订单
                mainOrderIv.setBackgroundResource(R.drawable.icon_dingdan_jianying_wenzi);
                mainIndexIv.setBackgroundResource(R.drawable.icon_shouye_jianying_wenzi);
                mainMyIv.setBackgroundResource(R.drawable.icon_wo_wenzi);
                break;
            case 1://首页
                mainOrderIv.setBackgroundResource(R.drawable.icon_dingdan_wenzi);
                mainIndexIv.setBackgroundResource(R.drawable.icon_shouye_wenzi);
                mainMyIv.setBackgroundResource(R.drawable.icon_wo_wenzi);
                break;
            case 2://我
                mainOrderIv.setBackgroundResource(R.drawable.icon_dingdan_wenzi);
                mainIndexIv.setBackgroundResource(R.drawable.icon_shouye_jianying_wenzi);
                mainMyIv.setBackgroundResource(R.drawable.icon_wo_jianying_wenzi);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_order:
                mainViewpager.setCurrentItem(0);
                mainOrderIv.setBackgroundResource(R.drawable.icon_dingdan_jianying_wenzi);
                mainIndexIv.setBackgroundResource(R.drawable.icon_shouye_jianying_wenzi);
                mainMyIv.setBackgroundResource(R.drawable.icon_wo_wenzi);
                break;
            case R.id.main_index:
                mainViewpager.setCurrentItem(1);
                mainOrderIv.setBackgroundResource(R.drawable.icon_dingdan_wenzi);
                mainIndexIv.setBackgroundResource(R.drawable.icon_shouye_wenzi);
                mainMyIv.setBackgroundResource(R.drawable.icon_wo_wenzi);
                break;
            case R.id.main_my:
                mainViewpager.setCurrentItem(2);
                mainOrderIv.setBackgroundResource(R.drawable.icon_dingdan_wenzi);
                mainIndexIv.setBackgroundResource(R.drawable.icon_shouye_jianying_wenzi);
                mainMyIv.setBackgroundResource(R.drawable.icon_wo_jianying_wenzi);
                break;
        }

    }

    //用于根据文件是否存在判断是否处在登录状态
    //用户的token信息保存的文件名为
    public void isLogin() {
        GetSPData getSPData = new GetSPData();
        String token = getSPData.getSPToken(MainActivity.this);
        userId = getSPData.getSPUserID(MainActivity.this);
        String telephone = getSPData.getSPTelephone(MainActivity.this);
        //验证,从sharedpreferences获取数据
        //验证,从静态变量获取数据
        Log.i(TAG, "isLogin: 登录验证sp " + token + " " + userId + " " + telephone);
        if (LoginStaticData.token.equals(token) && LoginStaticData.userId.equals(userId) & LoginStaticData.telephone.equals(telephone)) {
            //静态数据正确
        } else {
            //静态变量赋值
            if (token != null && userId != null & telephone != null) {
                LoginStaticData.token = token;
                LoginStaticData.userId = userId;
                LoginStaticData.telephone = telephone;
            }
            //验证一下静态信息
            Log.i(TAG, "isLogin: 登录验证静态数据(主页): " + LoginStaticData.token + " " + LoginStaticData.userId + " " + telephone);
        }
        if (token == null) {
            //本地没有token,这里有第一个跳转
            finish();
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        } else {
            //这里是未完待写的代码:根据本地token网络请求服务器,判断token是否合法,合法返回true则无需再次登录,不合法则还是返回false,为未登录状态,同时删除本地文件
            ChackToken(token, userId);
        }
    }

    private void ChackToken(String token, String userId) {
        mMvpPresenter.getToken(this, token, mMultipleStateView);
    }

    @Override
    public void getToken(TokenBean bean) {
        Log.e(TAG, "获取Token成功");
        if (userId.equals(bean.getUser_id())) {
            Log.i(TAG, "onResponse2: userId数据验证正确");
        } else {
            Log.i(TAG, "onResponse2: userId数据验证错误: " + "本地token请求获取的userId和本地userId不相同");
            DataSaveSP dataSaveSP = new DataSaveSP();
            boolean b = dataSaveSP.dataSave(userId, MainActivity.this);
            Log.i(TAG, "onResponse: userId覆盖结果" + b);
            LoginStaticData.userId = userId;
        }
        Log.i(TAG, "onResponse2: token验证通过");
    }

    @Override
    protected void onStart() {
        super.onStart();
        isLogin();//登录token验证
    }

}
