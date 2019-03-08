package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.BuyPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.HelpBuyTagBean;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.HelpBuyContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class HelpBuyPresenter extends HelpBuyContract.Presenter {


    @Override
    public void getItemsCategory(String city, String cate_id, String user_id, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getItemsCategory(city, cate_id, user_id), new RxObserverListener<ItemsCategoryBean>(mView) {
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
    public void placeOrder(String userId, String cateId, String endId, String gtypeId, String weight, String coupon, String couponId, String taskPrice,
                           String payFree, String gender, String deliveryTime, String remarks, String goods, String buy_address, String buy_lat,
                           String buy_lng,String  price ,final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.placeOrder(userId, cateId, endId, gtypeId, weight,
                coupon, couponId, taskPrice, payFree, gender, deliveryTime, remarks, goods, buy_address, buy_lat, buy_lng,price), new RxObserverListener<BuyPlaceOrderBean>(mView) {
            @Override
            public void onSuccess(BuyPlaceOrderBean result) {
                mView.placeOrder(result);
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
                    mView.isPrefEmpty();
                }
            }
        }));
    }

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
    public void payWeiXing(String order_id, String user_id, String pay_type, String pay_free, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payWeiXing(order_id, user_id, pay_type, pay_free), new RxObserverListener<WeixingPayBean>(mView) {
            @Override
            public void onSuccess(WeixingPayBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.payWeiXing(result);
            }
        }));
    }

    @Override
    public void payZhifubao(String order_id, String user_id, String pay_type, String pay_free, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payZhifubao(order_id, user_id, pay_type, pay_free), new RxObserverListener<ZhiFuBoPayBean>(mView) {
            @Override
            public void onSuccess(ZhiFuBoPayBean result) {
                mView.payZhifubao(result);
            }
        }));
    }

    @Override
    public void payYue(String order_id, String user_id, String pay_type, String pay_free, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payYue(order_id, user_id, pay_type, pay_free), new RxObserverListener<YuEBean>(mView) {
            @Override
            public void onSuccess(YuEBean result) {
                mView.payYue(result);
            }
        }));
    }

    @Override
    public void helpBuyTag(String gtype_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.helpBuyTag(gtype_id), new RxObserverListener<HelpBuyTagBean>(mView) {
            @Override
            public void onSuccess(HelpBuyTagBean result) {

                mView.helpBuyTag(result);
            }
        }));
    }
}
