package com.mouqukeji.hmdeer.presenter.fragment;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.ErrorBean;
import com.mouqukeji.hmdeer.bean.FinishOrderBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.fragment.OngingOrderContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class OngoingOrderPresenter extends OngingOrderContract.Presenter {
    @Override
    public void payYueInfo(String user_id, String order_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payYueInfo(user_id, order_id), new RxObserverListener<PayYueBean>(mView) {
            @Override
            public void onSuccess(PayYueBean result) {
                mView.payYueInfo(result);
            }
        }));
    }
    @Override
    public void getProgressIndent(String user_id, String procress,final MultipleStatusView multipleStatusView) {

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
    @Override
    public void finishOrder(String task_id,String user_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.finishOrder(task_id,user_id), new RxObserverListener<FinishOrderBean>(mView) {
            @Override
            public void onSuccess(FinishOrderBean result) {

                mView.finishOrder(result);
            }
        }));
    }

    @Override
    public void payAgainWeixin(String makeup_id, String user_id, String pay_type, String makeup_fee, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payAgainWeixin(makeup_id, user_id, pay_type, makeup_fee), new RxObserverListener<WeixingPayBean>(mView) {
            @Override
            public void onSuccess(WeixingPayBean result) {
                mView.payAgainWeixin(result);
            }
        }));
    }

    @Override
    public void payAgainZhiFuBao(String makeup_id, String user_id, String pay_type, String makeup_fee, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payAgainZhiFuBao(makeup_id, user_id, pay_type, makeup_fee), new RxObserverListener<ZhiFuBoPayBean>(mView) {
            @Override
            public void onSuccess(ZhiFuBoPayBean result) {
                mView.payAgainZhiFuBao(result);
            }
        }));
    }

    @Override
    public void payAgainYue(String makeup_id, String user_id, String pay_type, String makeup_fee, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payAgainYue(makeup_id, user_id, pay_type, makeup_fee), new RxObserverListener<YuEBean>(mView) {
            @Override
            public void onSuccess(YuEBean result) {
                mView.payAgainYue(result);
            }
        }));
    }
}

