package com.mouqu.zhailu.zhailu.contract.activity;


import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.PackageBean;
import com.mouqu.zhailu.zhailu.bean.PreferentialBean;
import com.mouqu.zhailu.zhailu.bean.UserImageBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

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
