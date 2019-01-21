package com.mouqu.zhailu.zhailu.presenter.activity;

import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.CodeCheckBean;
import com.mouqu.zhailu.zhailu.contract.activity.GetbackPw2Contract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class GetbackPw2Presenter extends  GetbackPw2Contract.Presenter{

    @Override
    public void checkCode(String number, String code, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.checkCode(number,code), new RxObserverListener<CodeCheckBean>(mView) {
            @Override
            public void onSuccess(CodeCheckBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.checkCode(result);
            }
        }));
    }
}

