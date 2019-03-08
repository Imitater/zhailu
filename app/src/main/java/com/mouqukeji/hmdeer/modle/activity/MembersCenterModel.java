package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.BuyVipBean;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.PackageBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.VipListBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.contract.activity.MembersCenterContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.activity.MemberCenterActivity;

import java.lang.reflect.Member;

import io.reactivex.Observable;

public class MembersCenterModel implements MembersCenterContract.Model {


    @Override
    public Observable<BaseHttpResponse<VipListBean>> vip() {
        return RetrofitManager.getInstance().getRequestService().vip();
    }

    @Override
    public Observable<BaseHttpResponse<BuyVipBean>> buyVip(String vip_id, String user_id, String pay_fee, String pay_type) {
        return RetrofitManager.getInstance().getRequestService().buyVip(vip_id,user_id,pay_fee,pay_type);
    }

    @Override
    public Observable<BaseHttpResponse<PackageBean>> getMoney(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getMoney(user_id);
    }
}
