package com.mouqukeji.hmdeer.presenter.activity;

import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.CodeCheckBean;
import com.mouqukeji.hmdeer.contract.activity.GetbackPw2Contract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class GetbackPw2Presenter extends  GetbackPw2Contract.Presenter{

    @Override
    public void checkCode(String number, String code, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.checkCode(number,code), new RxObserverListener<CodeCheckBean>(mView) {
            @Override
            public void onSuccess(CodeCheckBean result) {
                mView.checkCode(result);
            }
        }));
    }
}

