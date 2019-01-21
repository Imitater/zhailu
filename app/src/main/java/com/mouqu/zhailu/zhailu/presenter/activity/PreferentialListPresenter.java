package com.mouqu.zhailu.zhailu.presenter.activity;


import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.PreferentialBean;
import com.mouqu.zhailu.zhailu.contract.activity.PreferentialListContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class PreferentialListPresenter extends  PreferentialListContract.Presenter{

    @Override
    public void getPreferentialList(String user_id, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getPreferentialList(user_id), new RxObserverListener<PreferentialBean>(mView) {
            @Override
            public void onSuccess(PreferentialBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.getPreferentialList(result);
            }
        }));
    }
}

