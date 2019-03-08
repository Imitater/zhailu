package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PackageBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.HelpTakeContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class HelpTakePresenter extends HelpTakeContract.Presenter {


    @Override
    public void placeOrder(String user_id, String cate_id, String end_id, String[] pickup_code, String express_point, String gtype_id, String weight, String coupon, String coupon_id, String task_price, String pay_fee, String gender, String delivery_time, String remarks, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.placeOrder(user_id, cate_id, end_id, pickup_code, express_point, gtype_id, weight, coupon, coupon_id, task_price, pay_fee, gender, delivery_time, remarks), new RxObserverListener<PlaceOrderBean>(mView) {
            @Override
            public void onSuccess(PlaceOrderBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.placeOrder(result);
            }
        }));
    }

    @Override
    public void getItemsCategory(String city, String cate_id, String user_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getItemsCategory(city,cate_id,user_id), new RxObserverListener<ItemsCategoryBean>(mView) {
            @Override
            public void onSuccess(ItemsCategoryBean result) {
                if (result.getDefault_address()!=null) {
                    mView.getItemsCategory(result);
                }else{
                    mView.isEmpty();
                }
            }
        }));
    }

    @Override
    public void getPreferentialList(String user_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getPreferentialList(user_id), new RxObserverListener<PreferentialBean>(mView) {
            @Override
            public void onSuccess(PreferentialBean result) {
                        if (result.getCoupons()!=null) {
                            mView.getPreferentialList(result);
                        }else{
                            mView.isPreEmpty();
                        }
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
