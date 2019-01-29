package com.mouqukeji.hmdeer.presenter.fragment;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.ErrorBean;
import com.mouqukeji.hmdeer.contract.fragment.CompleteOrderContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class CompleteOrderPresenter extends CompleteOrderContract.Presenter {

    @Override
    public void getProgressIndent(String user_id,String procress,final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getProgressIndent(user_id,procress), new RxObserverListener<AllOrderBean>(mView) {
            @Override
            public void onSuccess(AllOrderBean result) {

                if (result.getTasks().size()!=0) {
                    mView.getProgressIndent(result);
                }else{
                    mView.getEmpty();
                }
            }

            @Override
            public void onBusinessError(ErrorBean errorBean) {
                if (errorBean.getMsg().equals("暂无订单")){
                    mView.getEmpty();
                 }
                super.onBusinessError(errorBean);
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

