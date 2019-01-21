package com.mouqu.zhailu.zhailu.contract.activity;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.AddressListBean;
import com.mouqu.zhailu.zhailu.bean.ItemsCategoryBean;
import com.mouqu.zhailu.zhailu.bean.PlaceOrderBean;
import com.mouqu.zhailu.zhailu.bean.PreferentialBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;

public interface HelpTakeContract {
    interface View extends IBaseView {
        void getAddressList(AddressListBean bean);
        void placeOrder(PlaceOrderBean bean);
        void getItemsCategory(ItemsCategoryBean bean);
        void getPreferentialList(PreferentialBean bean);
        void isEmpty();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AddressListBean>> getAddressList(String user_id);
        Observable<BaseHttpResponse<PlaceOrderBean>> placeOrder(String user_id, String cate_id,
                                                                String end_id, String[] pickup_code,
                                                                String express_point,String gtype_id,
                                                                String weight,String coupon,
                                                                String coupon_id,String task_price,
                                                                String pay_fee,String gender,
                                                                String delivery_time,String remarks);
        Observable<BaseHttpResponse<ItemsCategoryBean>> getItemsCategory(String city,String cate_id,String user_id);
        Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(String user_id);
    }

    abstract class Presenter extends BasePresenter<HelpTakeContract.View, HelpTakeContract.Model> {
        public abstract void getAddressList(String user_id,MultipleStatusView multipleStatusView);
        public abstract void placeOrder(String user_id, String cate_id,
                                        String end_id,  String[] pickup_code,
                                        String express_point,String gtype_id,
                                        String weight,String coupon,
                                        String coupon_id,String task_price,
                                        String pay_fee,String gender,
                                        String delivery_time,String remarks,MultipleStatusView multipleStatusView);
        public abstract void getItemsCategory(String city,String cate_id,String user_id,MultipleStatusView multipleStatusView);
        public abstract void getPreferentialList(String user_id,MultipleStatusView multipleStatusView);
    }
}
