package com.mouqu.zhailu.zhailu.net;


import com.mouqu.zhailu.zhailu.bean.AddAddressBean;
import com.mouqu.zhailu.zhailu.bean.AddressListBean;
import com.mouqu.zhailu.zhailu.bean.ChangePasswordBean;
import com.mouqu.zhailu.zhailu.bean.ChangePhoneBean;
import com.mouqu.zhailu.zhailu.bean.CodeBean;
import com.mouqu.zhailu.zhailu.bean.CodeCheckBean;
import com.mouqu.zhailu.zhailu.bean.AllOrderBean;
import com.mouqu.zhailu.zhailu.bean.EditAddressBean;
import com.mouqu.zhailu.zhailu.bean.IndexBean;
import com.mouqu.zhailu.zhailu.bean.ItemsCategoryBean;
import com.mouqu.zhailu.zhailu.bean.PackageBean;
import com.mouqu.zhailu.zhailu.bean.PlaceOrderBean;
import com.mouqu.zhailu.zhailu.bean.PreferentialBean;
import com.mouqu.zhailu.zhailu.bean.RegisteredBean;
import com.mouqu.zhailu.zhailu.bean.SigninBean;
import com.mouqu.zhailu.zhailu.bean.TokenBean;
import com.mouqu.zhailu.zhailu.bean.UserImageBean;
import com.mouqu.zhailu.zhailu.bean.resetPasswordBean;

import java.util.ArrayList;
import java.util.HashMap;

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
    Observable<BaseHttpResponse<SigninBean>> login(@Query("telephone") String telephone, @Query("password") String password);

    //获取验证码
    @GET("api/Login/getcode")
    Observable<BaseHttpResponse<CodeBean>> getCode(@Query("telephone") String telephone);

    //注册
    @GET("api/Login/register")
    Observable<BaseHttpResponse<RegisteredBean>> registered(@Query("telephone") String telephone, @Query("code") String code, @Query("password") String password);

    //修改手机号
    @GET("api/v1/user/")
    Observable<BaseHttpResponse<ChangePhoneBean>> changeNumber(@Query("user_id") String id, @Query("telephone") String telephone, @Query("code") String code);

    //修改密码
    @GET("api/v1/user/editPassword")
    Observable<BaseHttpResponse<ChangePasswordBean>> changePassword(@Query("user_id") String userId, @Query("password") String password, @Query("new_password") String newPassword);

    //验证码核对
    @GET("api/v1/Login/checkCode")
    Observable<BaseHttpResponse<CodeCheckBean>> checkCode(@Query("telephone") String telephone, @Query("code") String code);

    //重置密码
    @GET("api/v1/Login/reset")
    Observable<BaseHttpResponse<resetPasswordBean>> resetPassword(@Query("telephone") String telephone, @Query("code") String code, @Query("password") String password);

    //获取地址列表
    @GET("api/v1/Address/addressList")
    Observable<BaseHttpResponse<AddressListBean>> getAddressList(@Query("user_id") String userId);

    //修改地址
    @GET("api/v1/Address/editPost")
    Observable<BaseHttpResponse<EditAddressBean>> editAddress(@Query("user_id") String userId, @Query("id") String id,
                                                              @Query("name") String name, @Query("telephone") String telephone,
                                                              @Query("address") String address, @Query("detail") String detail,
                                                              @Query("is_default") String is_default);

    //帮忙取下单
    @POST("api/v1/Task/publish")
    Observable<BaseHttpResponse<PlaceOrderBean>> placeOrder(@Query("user_id") String userId, @Query("cate_id") String cateId,
                                                            @Query("end_id") String endId, @Query("pickup_code[]") String[] pickupCode,
                                                            @Query("express_point") String expressPoint, @Query("gtype_id") String gtypeId,
                                                            @Query("weight") String weight, @Query("coupon") String coupon, @Query("coupon_id") String couponId,
                                                            @Query("task_price") String taskPrice, @Query("pay_fee") String payFree, @Query("gender") String gender,
                                                            @Query("delivery_time") String deliveryTime, @Query("remarks") String remarks);

    //物品类型
    @GET("api/v1/Category/category")
    Observable<BaseHttpResponse<ItemsCategoryBean>> getItemsCategory(@Query("city") String city,@Query("cate_id") String cate_id,@Query("user_id") String user_id);

    //钱包余额
    @GET("api/v1/user_bill/wallet")
    Observable<BaseHttpResponse<PackageBean>> getMoney(@Query("user_id") String userId );

    //优惠券列表
     @GET("api/v1/Coupon/couponList")
    Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(@Query("user_id") String userId );

     //增加地址信息
    @GET("api/v1/Address/addPost")
    Observable<BaseHttpResponse<AddAddressBean>> addAddress(@Query("user_id") String userId , @Query("name") String name, @Query("telephone") String telephone, @Query("address") String address, @Query("detail") String detail, @Query("is_default") String is_default);
}