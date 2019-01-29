package com.mouqukeji.hmdeer.presenter.activity;

import android.app.Activity;
 import android.widget.Toast;

import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.bean.ErrorBean;
import com.mouqukeji.hmdeer.contract.activity.GetbackPw1Contract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class GetbackPw1Presenter extends  GetbackPw1Contract.Presenter{

    @Override
    public void getCode(final Activity activity, String number, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getCode(number), new RxObserverListener<CodeBean>(mView) {
            @Override
            public void onSuccess(CodeBean result) {
                mView.getCode(result);
            }

            @Override
            public void onBusinessError(ErrorBean errorBean) {
                Toast.makeText(activity, "短信验证码请求发送异常", Toast.LENGTH_SHORT).show();
                super.onBusinessError(errorBean);
            }



        }));
    }
}

