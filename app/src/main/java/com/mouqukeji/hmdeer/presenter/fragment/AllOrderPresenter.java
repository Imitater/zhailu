package com.mouqukeji.hmdeer.presenter.fragment;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.contract.fragment.AllOrderContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class AllOrderPresenter extends AllOrderContract.Presenter {

    @Override
    public void getIndent(String user_id,final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getIndent(user_id), new RxObserverListener<AllOrderBean>(mView) {
            @Override
            public void onSuccess(AllOrderBean result) {

                if (result.getTasks().size()!=0) {
                    mView.getIndent(result);
                }else{
                    mView.getEmpty();
                }
            }
        }));
    }

    @Override
    public void getIndentNext(String user_id, String progress, String page, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getIndentNext(user_id,progress,page), new RxObserverListener<AllOrderBean>(mView) {
            @Override
            public void onSuccess(AllOrderBean result) {

                mView.getIndentNext(result);
            }

        }));
    }

}

