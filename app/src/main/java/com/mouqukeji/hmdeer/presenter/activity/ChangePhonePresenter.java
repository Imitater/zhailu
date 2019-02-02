package com.mouqukeji.hmdeer.presenter.activity;


import android.app.Activity;
import android.widget.Toast;

import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.ChangePhoneBean;
import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.bean.ErrorBean;
import com.mouqukeji.hmdeer.contract.activity.ChangePhoneContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class ChangePhonePresenter extends  ChangePhoneContract.Presenter{

    @Override
    public void changePhone(String id, String number, String code, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.changePhone(id,number,code), new RxObserverListener<ChangePhoneBean>(mView) {
            @Override
            public void onSuccess(ChangePhoneBean result) {
                mView.changePhone(result);
            }
        }));
    }

    @Override
    public void getCode(final Activity activity, String number,String type, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getCode(number,type), new RxObserverListener<CodeBean>(mView) {
            @Override
            public void onSuccess(CodeBean result) {
                mView.getCode(result);
            }

            @Override
            public void onBusinessError(ErrorBean errorBean) {
                    if (errorBean.getCode().equals(10001)&&errorBean.getMsg().equals("该手机号已存在")) {
                        Toast.makeText(activity, "该手机号已存在", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(activity, "短信验证码请求发送异常", Toast.LENGTH_SHORT).show();
                    }
                    super.onBusinessError(errorBean);
            }
        }));
    }
}

