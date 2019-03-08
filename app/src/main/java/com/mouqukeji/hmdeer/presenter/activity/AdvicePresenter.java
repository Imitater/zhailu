package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.FeedBackBean;
import com.mouqukeji.hmdeer.bean.UserImageBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.contract.activity.AdviceContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class AdvicePresenter extends  AdviceContract.Presenter{
    @Override
    public void feedBack(String picture, String suggestion, String question, String userid, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.feedBack(picture,suggestion,question,userid), new RxObserverListener<FeedBackBean>(mView) {
            @Override
            public void onSuccess(FeedBackBean result) {
                mView.feedBack(result);
            }
        }));
    }
}

