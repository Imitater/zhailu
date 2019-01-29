package com.mouqukeji.hmdeer.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.ui.fragment.PayCompleteFragment;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.EventBusUtils;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.WxPayConfig;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import static com.mouqukeji.hmdeer.util.EventBusUtils.post;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, WxPayConfig.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.pay_result;
    }

    @Override
    protected void setUpView() {
        frameLayout = findViewById(R.id.framelayout);
        MyActionBar actionBar = findViewById(R.id.myactionbar);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("ddd", "onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == -2) {
                Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                finish();
            } else if (resp.errCode == -1) {
                Toast.makeText(this, "支付失败"+resp.errCode, Toast.LENGTH_SHORT).show();
            } else if (resp.errCode==0){
                //支付成功  发送消息
                Toast.makeText(this,"支付成功",Toast.LENGTH_SHORT).show();
                EventMessage eventMessage = new EventMessage(EventCode.EVENT_C, 1);
                post(eventMessage);
                finish();
            }
        }
    }


}
