package com.mouqukeji.hmdeer.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.CheckVersionBean;
import com.mouqukeji.hmdeer.contract.activity.AboutContract;
import com.mouqukeji.hmdeer.modle.activity.AboutModel;
import com.mouqukeji.hmdeer.presenter.activity.AboutPresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.update.ICheckAgent;
import com.mouqukeji.hmdeer.update.IUpdateChecker;
import com.mouqukeji.hmdeer.update.IUpdateParser;
import com.mouqukeji.hmdeer.update.UpdateInfo;
import com.mouqukeji.hmdeer.update.UpdateManager;
import com.mouqukeji.hmdeer.util.PhoneUtils;

import butterknife.BindView;

public class AboutActivity extends BaseActivity<AboutPresenter, AboutModel> implements AboutContract.View, View.OnClickListener {
    @BindView(R.id.about_actionbar)
    MyActionBar aboutActionbar;
    @BindView(R.id.about_versiom)
    LinearLayout aboutVersiom;
    private String versionName;

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

    void check(boolean isManual, final boolean hasUpdate, final boolean isSilent,
               final boolean isIgnorable, final int notifyId, final String apk_md5,final String size,final String url,final String versionName,final String upInfo
            ,final String isForce) {
        UpdateManager.create(this).setChecker(new IUpdateChecker() {
            @Override
            public void check(ICheckAgent agent, String url) {
                agent.setInfo("");
            }
        }).setWifiOnly(true).setUrl("https://api.hmdeer.com/api/Login/checkVersion?platform=android&version_code="+versionName).setManual(isManual)
                .setNotifyId(notifyId).setParser(new IUpdateParser() {
            @Override
            public UpdateInfo parse(String source) throws Exception {
                UpdateInfo info = new UpdateInfo();
                info.hasUpdate = hasUpdate;
                info.updateContent =upInfo;
                info.versionCode = 1;
                info.versionName = versionName;
                info.url = url;
                info.md5 = apk_md5;
                if (isForce.equals("0")){
                    info.isForce = false;
                }else{
                    info.isForce = true;
                }
                info.size=Long.parseLong(size);
                info.isIgnorable = isIgnorable;
                info.isSilent = isSilent;
                return info;
            }
        }).check();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.about_versiom:
                versionName = PhoneUtils.getVersionName(this);
                mMvpPresenter.checkVersion(this,"android", versionName, mMultipleStateView);
                break;
        }
    }

    @Override
    public void checkVersion(CheckVersionBean backBean) {
        if (!backBean.getVersionInfo().getVersion_code().equals(versionName)) {
             check(false, true, false,
                    false, 998,backBean.getVersionInfo().getApk_md5(),backBean.getVersionInfo().getApk_size(),backBean.getVersionInfo().getApk_url(),
                    backBean.getVersionInfo().getVersion_code(),backBean.getVersionInfo().getUpdate_info(),backBean.getVersionInfo().getIs_compel());
        }else{
            Toast.makeText(AboutActivity.this,"当前已是最新版",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void isNeedUp() {
        Toast.makeText(AboutActivity.this,"当前已是最新版",Toast.LENGTH_SHORT).show();
    }
}
