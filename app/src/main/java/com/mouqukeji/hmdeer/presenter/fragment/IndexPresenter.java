package com.mouqukeji.hmdeer.presenter.fragment;



import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.IndexBean;
import com.mouqukeji.hmdeer.contract.fragment.IndexContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class IndexPresenter extends IndexContract.Presenter {

    @Override
    public void getIndex(final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getIndex(), new RxObserverListener<IndexBean>(mView) {
            @Override
            public void onSuccess(IndexBean result) {

                mView.getIndex(result);
            }

        }));
    }
}

