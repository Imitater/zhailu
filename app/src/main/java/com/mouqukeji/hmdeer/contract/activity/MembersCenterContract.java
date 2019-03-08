package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.BuyVipBean;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.PackageBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.VipListBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;
import retrofit2.http.Query;

public interface MembersCenterContract {
    interface View extends IBaseView {
        void vip(VipListBean bean);
        void buyVip(BuyVipBean bean);
        void getMoney(PackageBean bean);

      }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<VipListBean>> vip();
        Observable<BaseHttpResponse<BuyVipBean>> buyVip(String vip_id,String user_id,String pay_fee,String pay_type);
        Observable<BaseHttpResponse<PackageBean>> getMoney(String user_id);
      }

    abstract class Presenter extends BasePresenter<MembersCenterContract.View, MembersCenterContract.Model> {
        public abstract void vip(MultipleStatusView multipleStatusView);
        public abstract void buyVip(String vip_id,String user_id,String pay_fee,String pay_type,MultipleStatusView multipleStatusView);
        public abstract void getMoney(String user_id,MultipleStatusView multipleStatusView);
      }
}
