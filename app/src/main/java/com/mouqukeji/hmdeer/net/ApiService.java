package com.mouqukeji.hmdeer.net;


import com.mouqukeji.hmdeer.bean.AddAddressBean;
import com.mouqukeji.hmdeer.bean.AddPostBean;
import com.mouqukeji.hmdeer.bean.AddressListBean;
import com.mouqukeji.hmdeer.bean.BuyPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.BuyVipBean;
import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.bean.ChangePasswordBean;
import com.mouqukeji.hmdeer.bean.ChangePhoneBean;
import com.mouqukeji.hmdeer.bean.CheckVersionBean;
import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.bean.CodeCheckBean;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.ConsumptionListBean;
import com.mouqukeji.hmdeer.bean.ConvertGoodsBean;
import com.mouqukeji.hmdeer.bean.DeleteAddressBean;
import com.mouqukeji.hmdeer.bean.DeleteOrderBean;
import com.mouqukeji.hmdeer.bean.DeliverPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.DetailIntegralBean;
import com.mouqukeji.hmdeer.bean.DrawIntegralBean;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.EvaluationBean;
import com.mouqukeji.hmdeer.bean.FeedBackBean;
import com.mouqukeji.hmdeer.bean.FinishOrderBean;
import com.mouqukeji.hmdeer.bean.HelpBuyInfoBean;
import com.mouqukeji.hmdeer.bean.HelpBuyTagBean;
import com.mouqukeji.hmdeer.bean.HelpDeliverInfoBean;
import com.mouqukeji.hmdeer.bean.HelpSendInfoBean;
import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;
import com.mouqukeji.hmdeer.bean.HelpUniversalInfoBean;
import com.mouqukeji.hmdeer.bean.IndexBean;
import com.mouqukeji.hmdeer.bean.IntegralListBean;
import com.mouqukeji.hmdeer.bean.IntegralPageBean;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.LocationDownBean;
import com.mouqukeji.hmdeer.bean.MyVipBean;
import com.mouqukeji.hmdeer.bean.PackageBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.bean.PushMesgBean;
import com.mouqukeji.hmdeer.bean.ReChargeBean;
import com.mouqukeji.hmdeer.bean.RechangeListBean;
import com.mouqukeji.hmdeer.bean.RechargePageBean;
import com.mouqukeji.hmdeer.bean.RegisteredBean;
import com.mouqukeji.hmdeer.bean.SendPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.SericeBean;
import com.mouqukeji.hmdeer.bean.SigninBean;
import com.mouqukeji.hmdeer.bean.TokenBean;
import com.mouqukeji.hmdeer.bean.TrackBean;
import com.mouqukeji.hmdeer.bean.UnviersalPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.UserImageBean;
import com.mouqukeji.hmdeer.bean.UserInfoBean;
import com.mouqukeji.hmdeer.bean.UserInfoUpBean;
import com.mouqukeji.hmdeer.bean.VipListBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.bean.resetPasswordBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {
    //首页
    @GET("api/v1/Index")
    Observable<BaseHttpResponse<IndexBean>> getIndex();

    //订单列表 progress 类型
    @GET("api/v1/task/taskList")
    Observable<BaseHttpResponse<AllOrderBean>> getProgressIndent(@Query("user_id") String id, @Query("progress") String progress);

    //订单列表 progress 类型 page页码
    @GET("api/v1/task/taskList")
    Observable<BaseHttpResponse<AllOrderBean>> getIndentNext(@Query("user_id") String id, @Query("progress") String progress, @Query("page") String page);

    //订单列表  默认progress 0
    @GET("api/v1/task/taskList")
    Observable<BaseHttpResponse<AllOrderBean>> getIndent(@Query("user_id") String id);

    //获取用户头像
    @GET("api/v1/user/personal")
    Observable<BaseHttpResponse<UserImageBean>> getUserImage(@Query("user_id") String id);

    //token对比
    @GET("api/Login/tokenCheck")
    Observable<BaseHttpResponse<TokenBean>> getToken(@Query("token") String token);

    //登录
    @GET("api/Login/Login")
    Observable<BaseHttpResponse<SigninBean>> login(@Query("telephone") String telephone, @Query("password") String password, @Query("did") String did,
                                                   @Query("platform") String platform,@Query("app_version") String app_version,@Query("device_model") String device_model,
                                                   @Query("os_version") String os_version);

    //获取验证码
    @GET("api/login/telCode")
    Observable<BaseHttpResponse<CodeBean>> getCode(@Query("telephone") String telephone, @Query("type") String type);

    //注册
    @GET("api/Login/register")
    Observable<BaseHttpResponse<RegisteredBean>> registered(@Query("telephone") String telephone, @Query("code") String code, @Query("password") String password, @Query("school_name") String school_name);

    //修改手机号
    @GET("api/v1/User/editMobile")
    Observable<BaseHttpResponse<ChangePhoneBean>> changeNumber(@Query("user_id") String id, @Query("telephone") String telephone, @Query("code") String code);

    //修改密码
    @GET("api/v1/user/editPassword")
    Observable<BaseHttpResponse<ChangePasswordBean>> changePassword(@Query("user_id") String userId, @Query("password") String password, @Query("new_password") String newPassword);

    //验证码核对
    @GET("api/login/checkCode")
    Observable<BaseHttpResponse<CodeCheckBean>> checkCode(@Query("telephone") String telephone, @Query("code") String code);

    //重置密码
    @GET("api/Login/reset")
    Observable<BaseHttpResponse<resetPasswordBean>> resetPassword(@Query("telephone") String telephone, @Query("code") String code, @Query("password") String password);

    //获取地址列表
    @GET("api/v1/Address/addressList")
    Observable<BaseHttpResponse<AddressListBean>> getAddressList(@Query("user_id") String userId);


    //帮忙取下单
    @GET("api/v1/Task/publish")
    Observable<BaseHttpResponse<PlaceOrderBean>> placeOrder(@Query("user_id") String userId, @Query("cate_id") String cateId,
                                                            @Query("end_id") String endId, @Query("pickup_code[]") String[] pickupCode,
                                                            @Query("express_point") String expressPoint, @Query("gtype_id") String gtypeId,
                                                            @Query("weight") String weight, @Query("coupon") String coupon, @Query("coupon_id") String couponId,
                                                            @Query("task_price") String taskPrice, @Query("pay_fee") String payFree, @Query("gender") String gender,
                                                            @Query("delivery_time") String deliveryTime, @Query("remarks") String remarks);

    //物品类型
    @GET("api/v1/Category/category")
    Observable<BaseHttpResponse<ItemsCategoryBean>> getItemsCategory(@Query("city") String city, @Query("cate_id") String cate_id, @Query("user_id") String user_id);

    //钱包余额
    @GET("api/v1/user_bill/wallet")
    Observable<BaseHttpResponse<PackageBean>> getMoney(@Query("user_id") String userId);

    //优惠券列表
    @GET("api/v1/Coupon/couponList")
    Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(@Query("user_id") String userId);

    //增加地址信息
    @GET("api/v1/Address/addPost")
    Observable<BaseHttpResponse<AddAddressBean>> addAddress(@Query("user_id") String userId, @Query("name") String name, @Query("telephone") String telephone, @Query("address") String address, @Query("detail") String detail, @Query("is_default") String is_default, @Query("lat") String lat, @Query("lng") String lng); //增加地址信息

    //修改地址
    @GET("api/v1/Address/editPost")
    Observable<BaseHttpResponse<EditAddressBean>> editAddress(@Query("user_id") String userId, @Query("id") String id, @Query("name") String name, @Query("telephone") String telephone, @Query("address") String address, @Query("detail") String detail, @Query("is_default") String is_default, @Query("lat") String lat, @Query("lng") String lng);

    @GET("api/v1/Address/editPost")
    Observable<BaseHttpResponse<EditAddressBean>> editAddress(@Query("user_id") String userId, @Query("id") String id, @Query("name") String name, @Query("telephone") String telephone, @Query("address") String address, @Query("detail") String detail, @Query("is_default") String is_default);

    //订单支付微信接口
    @GET("api/v1/Task/payTask")
    Observable<BaseHttpResponse<WeixingPayBean>> payWeiXingOrder(@Query("order_id") String order_id, @Query("user_id") String user_id, @Query("pay_type") String pay_type, @Query("pay_fee") String pay_fee);

    //订单支付微信接口
    @GET("api/v1/Task/payTask")
    Observable<BaseHttpResponse<ZhiFuBoPayBean>> payZhiFuBaoOrder(@Query("order_id") String order_id, @Query("user_id") String user_id, @Query("pay_type") String pay_type, @Query("pay_fee") String pay_fee);

    //订单支余额接口
    @GET("api/v1/Task/payTask")
    Observable<BaseHttpResponse<YuEBean>> payYuEOrder(@Query("order_id") String order_id, @Query("user_id") String user_id, @Query("pay_type") String pay_type, @Query("pay_fee") String pay_fee);

    //充值接口
    @GET("api/v1/user_bill/rechargeBuy")
    Observable<BaseHttpResponse<ReChargeBean>> reCharge(@Query("user_id") String user_id, @Query("price") String price, @Query("pay_fee") String pay_fee, @Query("pay_type") String pay_type);

    //支付余额接口
    @GET("api/v1/Task/payPage")
    Observable<BaseHttpResponse<PayYueBean>> payYueInfo(@Query("user_id") String userId, @Query("order_id") String orderId);

    //帮忙取订单详情
    @GET("api/v1/Task/details")
    Observable<BaseHttpResponse<HelpTakeInfoBean>> helpTakeInfo(@Query("task_id") String taskId, @Query("cate_id") String cateId);

    //万能帮订单详情
    @GET("api/v1/Task/details")
    Observable<BaseHttpResponse<HelpUniversalInfoBean>> helpUniversalInfo(@Query("task_id") String taskId, @Query("cate_id") String cateId);

    //帮忙寄订单详情
    @GET("api/v1/Task/details")
    Observable<BaseHttpResponse<HelpSendInfoBean>> helpSendInfo(@Query("task_id") String taskId, @Query("cate_id") String cateId);

    //帮忙买订单详情
    @GET("api/v1/Task/details")
    Observable<BaseHttpResponse<HelpBuyInfoBean>> helpBuyInfo(@Query("task_id") String taskId, @Query("cate_id") String cateId);

    //帮忙送订单详情
    @GET("api/v1/Task/details")
    Observable<BaseHttpResponse<HelpDeliverInfoBean>> helpDeliverInfo(@Query("task_id") String taskId, @Query("cate_id") String cateId);

    //帮忙买下单
    @GET("api/v1/Task/publish")
    Observable<BaseHttpResponse<BuyPlaceOrderBean>> buyPlaceOrder(@Query("user_id") String userId, @Query("cate_id") String cateId,
                                                                  @Query("end_id") String endId, @Query("gtype_id") String gtypeId,
                                                                  @Query("weight") String weight, @Query("coupon") String coupon, @Query("coupon_id") String couponId,
                                                                  @Query("task_price") String taskPrice, @Query("pay_fee") String payFree, @Query("gender") String gender,
                                                                  @Query("delivery_time") String deliveryTime, @Query("remarks") String remarks, @Query("goods") String goods,
                                                                  @Query("buy_address") String buy_address, @Query("buy_lat") String buy_lat, @Query("buy_lng") String buy_lng, @Query("price") String price);

    //帮忙买标签
    @GET("api/v1/Tags/getTags")
    Observable<BaseHttpResponse<HelpBuyTagBean>> helpBuyTag(@Query("gtype_id") String gtype_id);

    //二次支付微信接口
    @GET("api/v1/Task/payMakeup")
    Observable<BaseHttpResponse<WeixingPayBean>> payAgainWeixin(@Query("makeup_id") String makeup_id, @Query("user_id") String user_id, @Query("pay_type") String pay_type, @Query("makeup_fee") String makeup_fee);

    //二次支付支付宝接口
    @GET("api/v1/Task/payMakeup")
    Observable<BaseHttpResponse<ZhiFuBoPayBean>> payAgainZhiFuBao(@Query("makeup_id") String makeup_id, @Query("user_id") String user_id, @Query("pay_type") String pay_type, @Query("makeup_fee") String makeup_fee);

    //二次支付接口
    @GET("api/v1/Task/payMakeup")
    Observable<BaseHttpResponse<YuEBean>> payAgainYue(@Query("makeup_id") String makeup_id, @Query("user_id") String user_id, @Query("pay_type") String pay_type, @Query("makeup_fee") String makeup_fee);

    //评价
    @GET("api/v1/Evaluate/addPost")
    Observable<BaseHttpResponse<EvaluationBean>> evaluationOrder( @Query("user_id") String user_id,@Query("order_id") String order_id, @Query("score") String score, @Query("content") String content);


    //帮忙寄下单
    @GET("api/v1/Task/publish")
    Observable<BaseHttpResponse<SendPlaceOrderBean>> sendPlaceOrder(@Query("user_id") String userId, @Query("cate_id") String cateId,
                                                                    @Query("start_id") String startId, @Query("express_point") String expressPoint, @Query("gtype_id") String gtypeId,
                                                                    @Query("weight") String weight, @Query("coupon") String coupon, @Query("coupon_id") String couponId,
                                                                    @Query("task_price") String taskPrice, @Query("pay_fee") String payFree,
                                                                    @Query("delivery_time") String deliveryTime, @Query("remarks") String remarks, @Query("express_pay_type") String expressPayType,
                                                                    @Query("name") String name, @Query("telephone") String telephone, @Query("address") String address, @Query("detail") String detail);


    //帮忙送下单
    @GET("api/v1/Task/publish")
    Observable<BaseHttpResponse<DeliverPlaceOrderBean>> deliverPlaceOrder(@Query("user_id") String userId, @Query("cate_id") String cateId,
                                                                          @Query("start_id") String startId, @Query("end_id") String end_id, @Query("gtype_id") String gtypeId,
                                                                          @Query("weight") String weight, @Query("coupon") String coupon, @Query("coupon_id") String couponId,
                                                                          @Query("task_price") String taskPrice, @Query("pay_fee") String payFree,
                                                                          @Query("gender") String gender, @Query("delivery_time") String delivery_time, @Query("remarks") String remarks);

    //万能帮下单
    @GET("api/v1/Task/publish")
    Observable<BaseHttpResponse<UnviersalPlaceOrderBean>> universalPlaceOrder(@Query("user_id") String user_id, @Query("cate_id") String cate_id, @Query("end_id") String end_id,
                                                                              @Query("demand") String demand, @Query("task_price") String task_price, @Query("pay_fee") String pay_fee,
                                                                              @Query("gender") String gender, @Query("delivery_time") String delivery_time);

    //充值明细
    @GET("api/v1/user_bill/recharge")
    Observable<BaseHttpResponse<RechangeListBean>> getRechangeList(@Query("user_id") String user_id, @Query("page") String page);


    //充值明细
    @GET("api/v1/user_bill/recharge")
    Observable<BaseHttpResponse<RechangeListBean>> getRechangeListNext(@Query("user_id") String user_id, @Query("page") String page);

    //消费明细
    @GET("api/v1/user_bill/buy")
    Observable<BaseHttpResponse<ConsumptionListBean>> getConsumptionList(@Query("user_id") String user_id, @Query("page") String page);

    //消费明细
    @GET("api/v1/user_bill/buy")
    Observable<BaseHttpResponse<ConsumptionListBean>> getConsumptionListNext(@Query("user_id") String user_id, @Query("page") String page);

    //意见反馈
    @GET("api/v1/feedback/feedbackAdd")
    Observable<BaseHttpResponse<FeedBackBean>> feedBack(@Query("picture") String picture, @Query("suggestion") String suggestion,
                                                        @Query("question") String question, @Query("user_id") String user_id);


    //个人资料修改页面
    @GET("api/v1/User/userPage")
    Observable<BaseHttpResponse<UserInfoBean>> getUserInfo(@Query("user_id") String user_id);

    //个人资料页面提交
    @GET("api/v1/User/updateInfo")
    Observable<BaseHttpResponse<UserInfoUpBean>> putUserInfo(@Query("user_id") String user_id, @Query("nickname") String nickname, @Query("avatar") String avatar, @Query("gender") String gender, @Query("age") String age, @Query("school_name") String school_name);

    //删除地址
    @GET("api/v1/Address/delete")
    Observable<BaseHttpResponse<DeleteAddressBean>> deleteAddress(@Query("user_id") String user_id, @Query("id") String id);

    //充值界面
    @GET("api/v1/user_bill/rechargePage")
    Observable<BaseHttpResponse<RechargePageBean>> rechangePage();

    //取消订单
    @GET("api/v1/Task/cancelTask")
    Observable<BaseHttpResponse<CancelOrderBean>> cancelOrder(@Query("task_id") String task_id, @Query("user_id") String user_id);

    //获取位置信息
    @GET("api/v1/User/latLng")
    Observable<BaseHttpResponse<LocationDownBean>> locationDown(@Query("user_id") String user_id, @Query("lat") String lat, @Query("lng") String lng, @Query("server_id") String server_id);

    //完成订单
    @GET("api/v1/Task/confirmFinish")
    Observable<BaseHttpResponse<FinishOrderBean>> finishOrder(@Query("task_id") String task_id,@Query("user_id") String user_id);

    //删除订单
    @GET("api/v1/Task/delete")
    Observable<BaseHttpResponse<DeleteOrderBean>> deleteOrder(@Query("task_id") String task_id);

    //积分列表
    @GET("api/v1/integral/integralList")
    Observable<BaseHttpResponse<IntegralListBean>> integralList(@Query("user_id") String user_id);

    //积分页面
    @GET("api/v1/integral/integralPage")
    Observable<BaseHttpResponse<IntegralPageBean>> integralPage(@Query("user_id") String user_id);

    //积分领取
     @GET("api/v1/integral/draw")
    Observable<BaseHttpResponse<DrawIntegralBean>> drawIntegral(@Query("user_id") String user_id);

     //物品详情
     @GET("api/v1/integral/detail")
    Observable<BaseHttpResponse<DetailIntegralBean>> detailIntegral(@Query("goods_id") String goods_id);

     //商品兑换
    @GET("api/v1/integral/convertGoods")
    Observable<BaseHttpResponse<ConvertGoodsBean>> convertGoods(@Query("goods_id") String goods_id,@Query("user_id") String user_id);

    //我的会员卡
    @GET("api/v1/Vip/myVip")
    Observable<BaseHttpResponse<MyVipBean>> myVip(@Query("user_id") String user_id);

    //会员卡列表
    @GET("api/v1/Vip/vip")
    Observable<BaseHttpResponse<VipListBean>> vip();

    //购买会员
     @GET("api/v1/Vip/buyVip")
    Observable<BaseHttpResponse<BuyVipBean>> buyVip(@Query("vip_id") String vip_id, @Query("user_id") String user_id, @Query("pay_fee") String pay_fee, @Query("pay_type") String pay_type);

    //校园代理申请
    @GET("api/v1/school_apply/addPost")
    Observable<BaseHttpResponse<AddPostBean>> addPost(@Query("name") String name, @Query("school") String school, @Query("grade") String grade, @Query("mobile") String mobile, @Query("superiority") String superiority);

    //版本更新
    @GET("api/Login/checkVersion")
    Observable<BaseHttpResponse<CheckVersionBean>> checkVersion(@Query("platform") String platform, @Query("version_code") String version_code);

    //消息发送
    @GET("api/login/pushMsg")
    Observable<BaseHttpResponse<PushMesgBean>> pushMesg(@Query("registration_id") String registration_id,@Query("did") String did,@Query("uuid") String uuid);
}

