package com.mouqu.zhailu.zhailu.modle.activity;

 import com.mouqu.zhailu.zhailu.bean.IndexBean;
 import com.mouqu.zhailu.zhailu.bean.TokenBean;
 import com.mouqu.zhailu.zhailu.contract.activity.MainContract;
 import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
 import com.mouqu.zhailu.zhailu.net.RetrofitManager;

 import io.reactivex.Observable;


public class MainModel implements MainContract.Model {


 @Override
 public Observable<BaseHttpResponse<TokenBean>> getToken(String token) {
  return RetrofitManager.getInstance().getRequestService().getToken(token);
 }
}
