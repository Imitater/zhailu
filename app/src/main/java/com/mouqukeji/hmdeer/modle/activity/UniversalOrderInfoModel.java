package com.mouqukeji.hmdeer.modle.activity;


  import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;
  import com.mouqukeji.hmdeer.bean.HelpUniversalInfoBean;
  import com.mouqukeji.hmdeer.bean.LocationDownBean;
  import com.mouqukeji.hmdeer.contract.activity.UniversalOrderInfoContract;
  import com.mouqukeji.hmdeer.net.BaseHttpResponse;
  import com.mouqukeji.hmdeer.net.RetrofitManager;

  import io.reactivex.Observable;

public class UniversalOrderInfoModel implements UniversalOrderInfoContract.Model {


  @Override
  public Observable<BaseHttpResponse<HelpUniversalInfoBean>> getUniversalInfo(String task_id, String cate_id) {
    return RetrofitManager.getInstance().getRequestService().helpUniversalInfo(task_id, cate_id);
  }
  @Override
  public Observable<BaseHttpResponse<LocationDownBean>> locationDown(String user_id, String lat, String lng, String server_id) {
    return RetrofitManager.getInstance().getRequestService().locationDown(user_id, lat,lng,server_id);
  }

}
