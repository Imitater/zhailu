package com.mouqu.zhailu.zhailu.presenter.activity;


import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.SigninBean;
import com.mouqu.zhailu.zhailu.contract.activity.SignInContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class SignInPresenter extends  SignInContract.Presenter{

    @Override
    public void signIn(String number, String password, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.signIn(number,password), new RxObserverListener<SigninBean>(mView) {
            @Override
            public void onSuccess(SigninBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.signIn(result);
            }
        }));
    }
}

