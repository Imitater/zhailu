package com.mouqu.zhailu.zhailu.presenter.fragment;


import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.AllOrderBean;
import com.mouqu.zhailu.zhailu.bean.ErrorBean;
import com.mouqu.zhailu.zhailu.contract.fragment.ToEvaluateContract;
import com.mouqu.zhailu.zhailu.contract.fragment.WaitListContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class ToEvaluatetPresenter extends ToEvaluateContract.Presenter {

    @Override
    public void getProgressIndent(String user_id,String procress,final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getProgressIndent(user_id,procress), new RxObserverListener<AllOrderBean>(mView) {
            @Override
            public void onSuccess(AllOrderBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
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
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getIndentNext(user_id,progress,page), new RxObserverListener<AllOrderBean>(mView) {
            @Override
            public void onSuccess(AllOrderBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.getIndentNext(result);
            }
        }));
    }

}

