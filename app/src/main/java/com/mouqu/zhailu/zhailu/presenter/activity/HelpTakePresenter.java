package com.mouqu.zhailu.zhailu.presenter.activity;


import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.AddressListBean;
import com.mouqu.zhailu.zhailu.bean.ErrorBean;
import com.mouqu.zhailu.zhailu.bean.ItemsCategoryBean;
import com.mouqu.zhailu.zhailu.bean.PlaceOrderBean;
import com.mouqu.zhailu.zhailu.bean.PreferentialBean;
import com.mouqu.zhailu.zhailu.contract.activity.HelpTakeContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import java.util.ArrayList;
import java.util.HashMap;

public class HelpTakePresenter extends HelpTakeContract.Presenter {
    @Override
    public void getAddressList(String user_id, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getAddressList(user_id), new RxObserverListener<AddressListBean>(mView) {
            @Override
            public void onSuccess(AddressListBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                if (result.getAddress().size() != 0) {
                    mView.getAddressList(result);
                } else {
                    mView.isEmpty();
                }
            }
        }));
    }

    @Override
    public void placeOrder(String user_id, String cate_id, String end_id, String[] pickup_code, String express_point, String gtype_id, String weight, String coupon, String coupon_id, String task_price, String pay_fee, String gender, String delivery_time, String remarks, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
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
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getItemsCategory(city,cate_id,user_id), new RxObserverListener<ItemsCategoryBean>(mView) {
            @Override
            public void onSuccess(ItemsCategoryBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.getItemsCategory(result);
            }
        }));
    }

    @Override
    public void getPreferentialList(String user_id, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getPreferentialList(user_id), new RxObserverListener<PreferentialBean>(mView) {
            @Override
            public void onSuccess(PreferentialBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                     mView.getPreferentialList(result);
            }
        }));
    }
}
