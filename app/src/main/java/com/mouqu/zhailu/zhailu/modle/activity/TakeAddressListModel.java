package com.mouqu.zhailu.zhailu.modle.activity;

 import com.mouqu.zhailu.zhailu.bean.AddressListBean;
 import com.mouqu.zhailu.zhailu.bean.EditAddressBean;
 import com.mouqu.zhailu.zhailu.contract.activity.TakeAddressListContract;
 import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
 import com.mouqu.zhailu.zhailu.net.RetrofitManager;

 import io.reactivex.Observable;


public class TakeAddressListModel implements TakeAddressListContract.Model {

 @Override
 public Observable<BaseHttpResponse<AddressListBean>> getAddressList(String user_id) {
  return RetrofitManager.getInstance().getRequestService().getAddressList(user_id);
 }

 @Override
 public Observable<BaseHttpResponse<EditAddressBean>> editAddress(String user_id, String id, String name, String telephone, String address, String detail, String is_default) {
  return RetrofitManager.getInstance().getRequestService().editAddress(user_id,id,name,telephone,address,detail,is_default);

 }
}
