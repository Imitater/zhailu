package com.mouqukeji.hmdeer.modle.activity;

import com.mouqukeji.hmdeer.bean.TokenBean;
 import com.mouqukeji.hmdeer.contract.activity.SplashContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;


public class SplashModel implements SplashContract.Model {


 @Override
 public Observable<BaseHttpResponse<TokenBean>> getToken(String token) {
  return RetrofitManager.getInstance().getRequestService().getToken(token);
 }
}
