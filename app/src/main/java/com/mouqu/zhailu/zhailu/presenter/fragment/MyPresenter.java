package com.mouqu.zhailu.zhailu.presenter.fragment;


import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.UserImageBean;
import com.mouqu.zhailu.zhailu.contract.fragment.MyContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class MyPresenter extends MyContract.Presenter {

    @Override
    public void getUserImage(String user_id, final MultipleStatusView multipleStatusView) {
         if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getUserImage(user_id), new RxObserverListener<UserImageBean>(mView) {
            @Override
            public void onSuccess(UserImageBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.getUserImage(result);
            }
        }));
    }
}

