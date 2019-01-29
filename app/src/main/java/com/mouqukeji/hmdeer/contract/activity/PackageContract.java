package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.PackageBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface PackageContract {
    interface View extends IBaseView {
        void getMoney(PackageBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<PackageBean>> getMoney(String user_id);
    }

    abstract class Presenter extends BasePresenter<PackageContract.View, PackageContract.Model> {
        public abstract void getMoney(String user_id,MultipleStatusView multipleStatusView);
    }
}
