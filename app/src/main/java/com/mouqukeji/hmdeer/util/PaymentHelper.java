package com.mouqukeji.hmdeer.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import com.alipay.sdk.app.PayTask;
import com.mouqukeji.hmdeer.bean.BuyVipBean;
import com.mouqukeji.hmdeer.bean.ReChargeBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 *
 * 支付宝和微信支付工具类
 */
public class PaymentHelper {
    private static final int SDK_PAY_FLAG = 1;
    /**
     * 微信支付
     */
    public static void startWeChatPay(Activity activity, WeixingPayBean bean) {
        if (activity == null || bean == null) {
            return;
        }
        IWXAPI wxapi = WXAPIFactory.createWXAPI(activity, WxPayConfig.APP_ID, true);
        // 将该app注册到微信
        wxapi.registerApp(WxPayConfig.APP_ID);
        if (!wxapi.isWXAppInstalled()) {
            Toast.makeText(activity, "没有安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            JSONObject obj = new JSONObject(bean.getPay().getPayInfo());
            String prepayid = obj.getString("prepayid");
            String appid = obj.getString("appid");
            String partnerid = obj.getString("partnerid");
            String packag = obj.getString("package");
            String noncestr = obj.getString("noncestr");
            String timestamp = obj.getString("timestamp");
            String paySign = obj.getString("paySign");
            PayReq req = new PayReq(); //调起微信APP的对象
            if (!WxPayConfig.APP_ID.equals(appid)) {
                return;
            }
            req.appId = appid;
            req.partnerId = partnerid;
            req.prepayId = prepayid;
            req.nonceStr = noncestr;
            req.timeStamp = timestamp;
            req.packageValue = packag; //Sign=WXPay
            req.sign = paySign;
            wxapi.sendReq(req); //发送调起微信的请求
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 微信支付
     */
    public static void startWeChatPay(Activity activity, BuyVipBean bean) {
        if (activity == null || bean == null) {
            return;
        }
        IWXAPI wxapi = WXAPIFactory.createWXAPI(activity, WxPayConfig.APP_ID, true);
        // 将该app注册到微信
        wxapi.registerApp(WxPayConfig.APP_ID);
        if (!wxapi.isWXAppInstalled()) {
            Toast.makeText(activity, "没有安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            JSONObject obj = new JSONObject(bean.getPay().getPayInfo());
            String prepayid = obj.getString("prepayid");
            String appid = obj.getString("appid");
            String partnerid = obj.getString("partnerid");
            String packag = obj.getString("package");
            String noncestr = obj.getString("noncestr");
            String timestamp = obj.getString("timestamp");
            String paySign = obj.getString("paySign");
            PayReq req = new PayReq(); //调起微信APP的对象
            if (!WxPayConfig.APP_ID.equals(appid)) {
                return;
            }
            req.appId = appid;
            req.partnerId = partnerid;
            req.prepayId = prepayid;
            req.nonceStr = noncestr;
            req.timeStamp = timestamp;
            req.packageValue = packag; //Sign=WXPay
            req.sign = paySign;
            wxapi.sendReq(req); //发送调起微信的请求
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //支付宝支付
    public static void aliPay(final Activity activity, final String url, final Handler mHandler) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(url, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /*
    * 微信充值
    * */
    public static void reChargeWeChatPay(Activity activity, ReChargeBean bean) {
        if (activity == null || bean == null) {
            return;
        }

        IWXAPI wxapi = WXAPIFactory.createWXAPI(activity, WxPayConfig.APP_ID, true);
        // 将该app注册到微信
        wxapi.registerApp(WxPayConfig.APP_ID);
        if (!wxapi.isWXAppInstalled()) {
            Toast.makeText(activity, "没有安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            JSONObject obj = new JSONObject(bean.getPayInfo());
            String prepayid = obj.getString("prepayid");
            String appid = obj.getString("appid");
            String partnerid = obj.getString("partnerid");
            String packag = obj.getString("package");
            String noncestr = obj.getString("noncestr");
            String timestamp = obj.getString("timestamp");
            String paySign = obj.getString("paySign");
            PayReq req = new PayReq(); //调起微信APP的对象
            if (!WxPayConfig.APP_ID.equals(appid)) {
                return;
            }
            req.appId = appid;
            req.partnerId = partnerid;
            req.prepayId = prepayid;
            req.nonceStr = noncestr;
            req.timeStamp = timestamp;
            req.packageValue = packag; //Sign=WXPay
            req.sign = paySign;
            wxapi.sendReq(req); //发送调起微信的请求
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}