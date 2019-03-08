package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.DrawIntegralBean;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.IntegralListBean;
import com.mouqukeji.hmdeer.bean.IntegralPageBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface IntegralMallContract {
    interface View extends IBaseView {
        void integralPage(IntegralPageBean bean);
        void drawIntegral(DrawIntegralBean bean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<IntegralPageBean>> integralPage(String user_id);
        Observable<BaseHttpResponse<DrawIntegralBean>> drawIntegral(String user_id);
     }

    abstract class Presenter extends BasePresenter<IntegralMallContract.View, IntegralMallContract.Model> {
        public abstract void integralPage(String user_id ,MultipleStatusView multipleStatusView);
        public abstract void drawIntegral(String user_id ,MultipleStatusView multipleStatusView);
     }
}
