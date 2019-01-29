package com.mouqukeji.hmdeer.modle.activity;

 import com.mouqukeji.hmdeer.bean.HelpBuyInfoBean;
 import com.mouqukeji.hmdeer.bean.PayYueBean;
 import com.mouqukeji.hmdeer.bean.WeixingPayBean;
 import com.mouqukeji.hmdeer.bean.YuEBean;
 import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
 import com.mouqukeji.hmdeer.contract.activity.BuyOrderInfoContract;
 import com.mouqukeji.hmdeer.net.BaseHttpResponse;
 import com.mouqukeji.hmdeer.net.RetrofitManager;

 import io.reactivex.Observable;


public class BuyOrderInfoModel implements BuyOrderInfoContract.Model {
 @Override
 public Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id) {
  return RetrofitManager.getInstance().getRequestService().payYueInfo(user_id, order_id);
 }

 @Override
 public Observable<BaseHttpResponse<WeixingPayBean>> payAgainWeixin(String makeup_id, String user_id, String pay_type, String makeup_fee) {
  return RetrofitManager.getInstance().getRequestService().payWeiXingOrder(makeup_id, user_id, pay_type, makeup_fee);
 }

 @Override
 public Observable<BaseHttpResponse<ZhiFuBoPayBean>> payAgainZhiFuBao(String makeup_id, String user_id, String pay_type, String makeup_fee) {
  return RetrofitManager.getInstance().getRequestService().payZhiFuBaoOrder(makeup_id, user_id, pay_type, makeup_fee);
 }

 @Override
 public Observable<BaseHttpResponse<YuEBean>> payAgainYue(String makeup_id, String user_id, String pay_type, String makeup_fee) {
  return RetrofitManager.getInstance().getRequestService().payYuEOrder(makeup_id, user_id, pay_type, makeup_fee);
 }

 @Override
 public Observable<BaseHttpResponse<HelpBuyInfoBean>> helpBuyInfo(String taskId, String cateId) {
  return RetrofitManager.getInstance().getRequestService().helpBuyInfo(taskId, cateId);
 }
}
