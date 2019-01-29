package com.mouqukeji.hmdeer.presenter.fragment;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.RechangeListBean;
import com.mouqukeji.hmdeer.bean.UserImageBean;
import com.mouqukeji.hmdeer.contract.fragment.AllOrderContract;
import com.mouqukeji.hmdeer.contract.fragment.RechangeListContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class RechangeListPresenter extends RechangeListContract.Presenter {


    @Override
    public void getRechangeList(String user_id, String page, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getRechangeList(user_id,page), new RxObserverListener<RechangeListBean>(mView) {
            @Override
            public void onSuccess(RechangeListBean result) {

                if (result.getBalance().getRecharges().size() != 0) {
                    mView.getRechangeList(result);
                } else {
                    mView.isEmpty();
                }
            }
        }));
    }

    @Override
    public void getRechangeListNext(String user_id, String page, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getRechangeListNext(user_id,page), new RxObserverListener<RechangeListBean>(mView) {
            @Override
            public void onSuccess(RechangeListBean result) {

                if (result.getBalance().getRecharges().size() != 0) {
                    mView.getRechangeListNext(result);
                } else {
                    mView.isEmpty();
                }
            }
        }));
    }
}

