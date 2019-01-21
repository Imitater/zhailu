package com.mouqu.zhailu.zhailu.presenter.fragment;



import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.IndexBean;
import com.mouqu.zhailu.zhailu.contract.fragment.IndexContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class IndexPresenter extends IndexContract.Presenter {

    @Override
    public void getIndex(final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getIndex(), new RxObserverListener<IndexBean>(mView) {
            @Override
            public void onSuccess(IndexBean result) {
                 if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.getIndex(result);
            }

        }));
    }
}

