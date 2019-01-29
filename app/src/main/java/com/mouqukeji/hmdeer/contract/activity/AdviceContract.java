package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.FeedBackBean;
import com.mouqukeji.hmdeer.bean.UserImageBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface AdviceContract {
    interface View extends IBaseView {
        void feedBack(FeedBackBean backBean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<FeedBackBean>> feedBack(String picture,String suggestion,String question,String userid);
     }

    abstract class Presenter extends BasePresenter<AdviceContract.View, AdviceContract.Model> {
        public abstract void feedBack(String picture,String suggestion,String question,String userid,MultipleStatusView multipleStatusView);
     }
}
