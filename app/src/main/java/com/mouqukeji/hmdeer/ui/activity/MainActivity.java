package com.mouqukeji.hmdeer.ui.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.CheckVersionBean;
import com.mouqukeji.hmdeer.contract.activity.MainContract;
import com.mouqukeji.hmdeer.modle.activity.MainModel;
import com.mouqukeji.hmdeer.presenter.activity.MainPresenter;
import com.mouqukeji.hmdeer.ui.adapter.MainPageAdapter;
import com.mouqukeji.hmdeer.ui.fragment.IndentFragment;
import com.mouqukeji.hmdeer.ui.fragment.IndexFragment;
import com.mouqukeji.hmdeer.ui.fragment.MyFragment;
import com.mouqukeji.hmdeer.ui.widget.CustomViewPager;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.update.ICheckAgent;
import com.mouqukeji.hmdeer.update.IUpdateChecker;
import com.mouqukeji.hmdeer.update.IUpdateParser;
import com.mouqukeji.hmdeer.update.UpdateInfo;
import com.mouqukeji.hmdeer.update.UpdateManager;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.JPushUtil;
import com.mouqukeji.hmdeer.util.PhoneUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


//主页面activity
public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View, View.OnClickListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.main_actionbar)
    MyActionBar mainActionbar;
    @BindView(R.id.main_viewpager)
    CustomViewPager mainViewpager;
    @BindView(R.id.main_index_iv)
    ImageView mainIndexIv;
    @BindView(R.id.main_index_tv)
    TextView mainIndexTv;
    @BindView(R.id.main_index)
    LinearLayout mainIndex;
    @BindView(R.id.main_order_iv)
    ImageView mainOrderIv;
    @BindView(R.id.main_order_tv)
    TextView mainOrderTv;
    @BindView(R.id.main_order)
    LinearLayout mainOrder;
    @BindView(R.id.main_my_iv)
    ImageView mainMyIv;
    @BindView(R.id.main_my_tv)
    TextView mainMyTv;
    @BindView(R.id.main_my)
    LinearLayout mainMy;
    @BindView(R.id.main_a_layout)
    RelativeLayout mainALayout;
    private List<Fragment> list = new ArrayList();

    private static final int MSG_SET_ALIAS = 1001;
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.mouqukeji.hmdeer.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static boolean isForeground = false;
    private String spUserID;
    private String versionName;
    private boolean flag;

    @Override
    protected void initViewAndEvents() {
        versionName = PhoneUtils.getVersionName(this);
        mMvpPresenter.checkVersion(this,"android", versionName, mMultipleStateView);

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        spUserID = new GetSPData().getSPUserID(this);
        //fragment 页面加载
        initFragment();
        //设置viewpager
        mainViewpager.setAdapter(new MainPageAdapter(getSupportFragmentManager(), list));
        mainViewpager.setOffscreenPageLimit(0);//ViewPager设置预加载页面的个数方法
        //默认选择首页
        mainViewpager.setCurrentItem(0);
        //设置点击事件
        initListener();


        JPushInterface.init(getApplicationContext());
        setAlias();
        registerMessageReceiver();  // used for receive msg
    }

    //设置别名（可以是用户的id）
    private void setAlias() {
        //调用JPush API设置Alias
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, PhoneUtils.getPhoneMeid(this)));
    }

    private void initFragment() {
        list.add(new IndexFragment());
        list.add(new IndentFragment());
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
            case 1://订单
                mainViewpager.setCurrentItem(1);
                mainOrderIv.setBackgroundResource(R.mipmap.icon_dingdan_jainying);
                mainIndexIv.setBackgroundResource(R.mipmap.icon_shouye);
                mainMyIv.setBackgroundResource(R.mipmap.icon_wo);

                mainOrderTv.setTextColor(getResources().getColor(R.color.blue));
                mainIndexTv.setTextColor(getResources().getColor(R.color.black));
                mainMyTv.setTextColor(getResources().getColor(R.color.black));
                break;
            case 0://首页
                mainViewpager.setCurrentItem(0);
                mainOrderIv.setBackgroundResource(R.mipmap.icon_dingdan);
                mainIndexIv.setBackgroundResource(R.mipmap.icon_shouye_jainying);
                mainMyIv.setBackgroundResource(R.mipmap.icon_wo);

                mainOrderTv.setTextColor(getResources().getColor(R.color.black));
                mainIndexTv.setTextColor(getResources().getColor(R.color.blue));
                mainMyTv.setTextColor(getResources().getColor(R.color.black));
                break;
            case 2://我
                mainViewpager.setCurrentItem(2);
                mainOrderIv.setBackgroundResource(R.mipmap.icon_dingdan);
                mainIndexIv.setBackgroundResource(R.mipmap.icon_shouye);
                mainMyIv.setBackgroundResource(R.mipmap.icon_wo_jianying);

                mainOrderTv.setTextColor(getResources().getColor(R.color.black));
                mainIndexTv.setTextColor(getResources().getColor(R.color.black));
                mainMyTv.setTextColor(getResources().getColor(R.color.blue));
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
                mainViewpager.setCurrentItem(1);
                mainOrderIv.setBackgroundResource(R.mipmap.icon_dingdan_jainying);
                mainIndexIv.setBackgroundResource(R.mipmap.icon_shouye);
                mainMyIv.setBackgroundResource(R.mipmap.icon_wo);

                mainOrderTv.setTextColor(getResources().getColor(R.color.blue));
                mainIndexTv.setTextColor(getResources().getColor(R.color.black));
                mainMyTv.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.main_index:
                mainViewpager.setCurrentItem(0);
                mainOrderIv.setBackgroundResource(R.mipmap.icon_dingdan);
                mainIndexIv.setBackgroundResource(R.mipmap.icon_shouye_jainying);
                mainMyIv.setBackgroundResource(R.mipmap.icon_wo);

                mainOrderTv.setTextColor(getResources().getColor(R.color.black));
                mainIndexTv.setTextColor(getResources().getColor(R.color.blue));
                mainMyTv.setTextColor(getResources().getColor(R.color.black));
                break;
            case R.id.main_my:
                mainViewpager.setCurrentItem(2);
                mainOrderIv.setBackgroundResource(R.mipmap.icon_dingdan);
                mainIndexIv.setBackgroundResource(R.mipmap.icon_shouye);
                mainMyIv.setBackgroundResource(R.mipmap.icon_wo_jianying);

                mainOrderTv.setTextColor(getResources().getColor(R.color.black));
                mainIndexTv.setTextColor(getResources().getColor(R.color.black));
                mainMyTv.setTextColor(getResources().getColor(R.color.blue));
                break;
        }

    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_G) {
                //跳到订单列表
                mainViewpager.setCurrentItem(1);
                mainOrderIv.setBackgroundResource(R.mipmap.icon_dingdan_jainying);
                mainIndexIv.setBackgroundResource(R.mipmap.icon_shouye);
                mainMyIv.setBackgroundResource(R.mipmap.icon_wo);

                mainOrderTv.setTextColor(getResources().getColor(R.color.blue));
                mainIndexTv.setTextColor(getResources().getColor(R.color.black));
                mainMyTv.setTextColor(getResources().getColor(R.color.black));
            }else if (event.getCode() == EventCode.EVENT_H){
                //跳到订单列表
                mainViewpager.setCurrentItem(0);
                mainOrderIv.setBackgroundResource(R.mipmap.icon_dingdan);
                mainIndexIv.setBackgroundResource(R.mipmap.icon_shouye_jainying);
                mainMyIv.setBackgroundResource(R.mipmap.icon_wo);

                mainOrderTv.setTextColor(getResources().getColor(R.color.black));
                mainIndexTv.setTextColor(getResources().getColor(R.color.blue));
                mainMyTv.setTextColor(getResources().getColor(R.color.black));
            }
        }
    }

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }


    void check(boolean isManual, final boolean hasUpdate, final boolean isSilent,
               final boolean isIgnorable, final int notifyId, final String apk_md5,
               final String size, final String url,
               final String version_Name, final String upInfo
            , final boolean isForce) {
        UpdateManager.create(this).setChecker(new IUpdateChecker() {
            @Override
            public void check(ICheckAgent agent, String url) {
                agent.setInfo("");
            }
        }).setWifiOnly(true).setUrl("https://api.hmdeer.com/api/Login/checkVersion?platform=android&version_code=" + versionName).setManual(isManual)
                .setNotifyId(notifyId).setParser(new IUpdateParser() {
            @Override
            public UpdateInfo parse(String source) throws Exception {
                UpdateInfo info = new UpdateInfo();
                info.hasUpdate = hasUpdate;
                info.updateContent = upInfo;
                info.versionCode = 1;
                info.versionName = version_Name;
                info.url = url;
                info.md5 = apk_md5;
                info.isForce = isForce;
                info.size = Long.parseLong(size);
                info.isIgnorable = isIgnorable;
                info.isSilent = isSilent;
                return info;
            }
        }).check();
    }

    @Override
    public void checkVersion(CheckVersionBean backBean) {
        if (backBean.getVersionInfo().getIs_compel().equals("0")){
            flag = false;
        }else{
            flag=true;
        }
        if (!backBean.getVersionInfo().getVersion_code().equals(versionName)) {
            check(false, true, false,
                    false, 998, backBean.getVersionInfo().getApk_md5(), backBean.getVersionInfo().getApk_size(), backBean.getVersionInfo().getApk_url(),
                    backBean.getVersionInfo().getVersion_code(), backBean.getVersionInfo().getUpdate_info(), flag);
        }
    }

    @Override
    public void isNeedUp() {

    }


    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!JPushUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
            }
        }
    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Log.d("TAG", "Set alias in handler.");
                    JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
                    break;
                default:
                    Log.i("TAG", "Unhandled msg - " + msg.what);
            }
        }
    };

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i("TAG", logs);
                    break;
                case 6002:
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e("TAG", logs);
            }
        }

    };

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
        JPushInterface.onResume(this);
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
        JPushInterface.onPause(this);
    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {//退出程序
            this.finish();
            System.exit(0);
        }
    }
}
