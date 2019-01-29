package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
import com.mouqukeji.hmdeer.bean.UnviersalPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.HelpUniversalContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class HelpUniversalPresenter extends HelpUniversalContract.Presenter  {
    @Override
    public void universalPlaceOrder(String user_id, String cate_id, String end_id, String demand, String task_price, String pay_fee, String gender, String delivery_time, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.universalPlaceOrder(user_id,cate_id,end_id,demand,task_price,pay_fee,gender,delivery_time), new RxObserverListener<UnviersalPlaceOrderBean>(mView) {
            @Override
            public void onSuccess(UnviersalPlaceOrderBean result) {

                mView.universalPlaceOrder(result);
            }
        }));
    }

    @Override
    public void getItemsCategory(String city, String cate_id, String user_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getItemsCategory(city,cate_id,user_id), new RxObserverListener<ItemsCategoryBean>(mView) {
            @Override
            public void onSuccess(ItemsCategoryBean result) {

                if (result!=null) {
                    mView.getItemsCategory(result);
                }else{
                    mView.isEmpty();
                }
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
}
