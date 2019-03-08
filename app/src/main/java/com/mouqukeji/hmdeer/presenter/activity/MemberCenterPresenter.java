package com.mouqukeji.hmdeer.presenter.activity;

import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.BuyVipBean;
import com.mouqukeji.hmdeer.bean.MyVipBean;
import com.mouqukeji.hmdeer.bean.PackageBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.VipListBean;
import com.mouqukeji.hmdeer.contract.activity.MembersCenterContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class MemberCenterPresenter extends  MembersCenterContract.Presenter{

    @Override
    public void vip(MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.vip(), new RxObserverListener<VipListBean>(mView) {
            @Override
            public void onSuccess(VipListBean result) {
                mView.vip(result);
            }
        }));
    }

    @Override
    public void buyVip(String vip_id, String user_id, String pay_fee, String pay_type, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.buyVip(vip_id,user_id,pay_fee,pay_type), new RxObserverListener<BuyVipBean>(mView) {
            @Override
            public void onSuccess(BuyVipBean result) {
                mView.buyVip(result);
            }
        }));
    }

    @Override
    public void getMoney(String user_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getMoney(user_id), new RxObserverListener<PackageBean>(mView) {
            @Override
            public void onSuccess(PackageBean result) {

                mView.getMoney(result);
            }
        }));
    }

}

