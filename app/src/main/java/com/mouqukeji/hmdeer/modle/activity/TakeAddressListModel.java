package com.mouqukeji.hmdeer.modle.activity;

 import com.mouqukeji.hmdeer.bean.AddressListBean;
 import com.mouqukeji.hmdeer.bean.EditAddressBean;
 import com.mouqukeji.hmdeer.contract.activity.TakeAddressListContract;
 import com.mouqukeji.hmdeer.net.BaseHttpResponse;
 import com.mouqukeji.hmdeer.net.RetrofitManager;

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
