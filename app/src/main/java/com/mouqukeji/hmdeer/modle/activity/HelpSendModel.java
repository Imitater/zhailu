package com.mouqukeji.hmdeer.modle.activity;

 import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
 import com.mouqukeji.hmdeer.bean.PayYueBean;
 import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
 import com.mouqukeji.hmdeer.bean.PreferentialBean;
 import com.mouqukeji.hmdeer.bean.SendPlaceOrderBean;
 import com.mouqukeji.hmdeer.bean.WeixingPayBean;
 import com.mouqukeji.hmdeer.bean.YuEBean;
 import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
 import com.mouqukeji.hmdeer.contract.activity.HelpSendContract;
 import com.mouqukeji.hmdeer.net.BaseHttpResponse;
 import com.mouqukeji.hmdeer.net.RetrofitManager;

 import io.reactivex.Observable;


public class HelpSendModel implements HelpSendContract.Model {

 @Override
 public Observable<BaseHttpResponse<SendPlaceOrderBean>> sendPlaceOrder(String userId, String cateId,
                                                                        String startId, String expressPoint, String gtypeId,
                                                                        String weight, String coupon, String couponId,
                                                                        String taskPrice, String payFree,
                                                                        String deliveryTime, String remarks, String expressPayType,
                                                                        String name, String telephone, String address, String detail) {
  return RetrofitManager.getInstance().getRequestService().sendPlaceOrder(userId,cateId,startId,expressPoint,gtypeId,weight,coupon,couponId,
          taskPrice,payFree,deliveryTime,remarks,expressPayType,name,telephone,address,detail);
 }

 @Override
 public Observable<BaseHttpResponse<ItemsCategoryBean>> getItemsCategory(String city, String cate_id, String user_id) {
  return RetrofitManager.getInstance().getRequestService().getItemsCategory(city, cate_id, user_id);
 }

 @Override
 public Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(String user_id) {
  return RetrofitManager.getInstance().getRequestService().getPreferentialList(user_id);
 }

 @Override
 public Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id) {
  return RetrofitManager.getInstance().getRequestService().payYueInfo(user_id, order_id);
 }

 @Override
 public Observable<BaseHttpResponse<WeixingPayBean>> payWeiXing(String order_id, String user_id, String pay_type, String pay_free) {
  return RetrofitManager.getInstance().getRequestService().payWeiXingOrder(order_id, user_id, pay_type, pay_free);
 }

 @Override
 public Observable<BaseHttpResponse<ZhiFuBoPayBean>> payZhifubao(String order_id, String user_id, String pay_type, String pay_free) {
  return RetrofitManager.getInstance().getRequestService().payZhiFuBaoOrder(order_id, user_id, pay_type, pay_free);
 }

 @Override
 public Observable<BaseHttpResponse<YuEBean>> payYue(String order_id, String user_id, String pay_type, String pay_free) {
  return RetrofitManager.getInstance().getRequestService().payYuEOrder(order_id, user_id, pay_type, pay_free);
 }
}
