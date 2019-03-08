package com.mouqukeji.hmdeer.presenter.activity;
import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.bean.HelpDeliverInfoBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.DeliverIngOrderInfoContract;
import com.mouqukeji.hmdeer.contract.activity.DeliverOrderInfoContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class DeliverIngOrderInfoPresenter extends DeliverIngOrderInfoContract.Presenter {
    @Override
    public void getDeliverInfo(String task_id, String cate_id, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getDeliverInfo(task_id, cate_id), new RxObserverListener<HelpDeliverInfoBean>(mView) {
            @Override
            public void onSuccess(HelpDeliverInfoBean result) {
                mView.getDeliverInfo(result);
            }
        }));
    }
    @Override
    public void cancelOrder(String task_id, String user_id,MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.cancelOrder(task_id,user_id), new RxObserverListener<CancelOrderBean>(mView) {
            @Override
            public void onSuccess(CancelOrderBean result) {

                mView.cancelOrder(result);
            }
        }));
    }
    @Override
    public void payWeiXing(String order_id, String user_id, String pay_type, String pay_free, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payWeiXing(order_id,user_id,pay_type,pay_free), new RxObserverListener<WeixingPayBean>(mView) {
            @Override
            public void onSuccess(WeixingPayBean result) {

                mView.payWeiXing(result);
            }
        }));
    }

    @Override
    public void payZhifubao(String order_id, String user_id, String pay_type, String pay_free, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payZhifubao(order_id,user_id,pay_type,pay_free), new RxObserverListener<ZhiFuBoPayBean>(mView) {
            @Override
            public void onSuccess(ZhiFuBoPayBean result) {

                mView.payZhifubao(result);
            }
        }));
    }

    @Override
    public void payYue(String order_id, String user_id, String pay_type, String pay_free, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payYue(order_id,user_id,pay_type,pay_free), new RxObserverListener<YuEBean>(mView) {
            @Override
            public void onSuccess(YuEBean result) {

                mView.payYue(result);
            }
        }));
    }

    @Override
    public void payYueInfo(String user_id,String order_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payYueInfo(user_id,order_id), new RxObserverListener<PayYueBean>(mView) {
            @Override
            public void onSuccess(PayYueBean result) {

                mView.payYueInfo(result);
            }
        }));
    }
}
