package com.mouqukeji.hmdeer.modle.fragment;

 import com.mouqukeji.hmdeer.bean.AddAddressBean;
 import com.mouqukeji.hmdeer.contract.fragment.ReceiverContract;
 import com.mouqukeji.hmdeer.net.BaseHttpResponse;
 import com.mouqukeji.hmdeer.net.RetrofitManager;

 import io.reactivex.Observable;


public class ReceiverModel implements ReceiverContract.Model {

 @Override
 public Observable<BaseHttpResponse<AddAddressBean>> addAddress(String user_id, String name, String telephone, String address, String detail, String is_default,String lat,String lng) {
  return RetrofitManager.getInstance().getRequestService().addAddress(user_id,name,telephone,address,detail,is_default,lat,lng);
 }
}
