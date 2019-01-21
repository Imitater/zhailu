package com.mouqu.zhailu.zhailu.modle.activity;

 import com.mouqu.zhailu.zhailu.bean.AddressListBean;
 import com.mouqu.zhailu.zhailu.bean.ItemsCategoryBean;
 import com.mouqu.zhailu.zhailu.bean.PlaceOrderBean;
 import com.mouqu.zhailu.zhailu.bean.PreferentialBean;
 import com.mouqu.zhailu.zhailu.contract.activity.HelpTakeContract;
 import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
 import com.mouqu.zhailu.zhailu.net.RetrofitManager;

 import java.util.ArrayList;
 import java.util.HashMap;

 import io.reactivex.Observable;


public class HelpTakeModel implements HelpTakeContract.Model {

 @Override
 public Observable<BaseHttpResponse<AddressListBean>> getAddressList(String user_id) {
  return RetrofitManager.getInstance().getRequestService().getAddressList(user_id);
 }

 @Override
 public Observable<BaseHttpResponse<PlaceOrderBean>> placeOrder(String user_id, String cate_id, String end_id, String[] pickup_code, String express_point, String gtype_id, String weight, String coupon, String coupon_id, String task_price, String pay_fee, String gender, String delivery_time, String remarks) {
  return RetrofitManager.getInstance().getRequestService().placeOrder(user_id,cate_id,end_id,pickup_code,express_point,gtype_id,weight,coupon,coupon_id,task_price,pay_fee,gender,delivery_time,remarks);
 }

 @Override
 public Observable<BaseHttpResponse<ItemsCategoryBean>> getItemsCategory(String city, String cate_id, String user_id) {
  return RetrofitManager.getInstance().getRequestService().getItemsCategory(city,cate_id,user_id);
 }

 @Override
 public Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(String user_id) {
  return RetrofitManager.getInstance().getRequestService().getPreferentialList(user_id);
 }
}
