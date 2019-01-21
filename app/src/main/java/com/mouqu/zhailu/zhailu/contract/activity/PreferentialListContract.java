package com.mouqu.zhailu.zhailu.contract.activity;


import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.PackageBean;
import com.mouqu.zhailu.zhailu.bean.PreferentialBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface PreferentialListContract {
    interface View extends IBaseView {
        void getPreferentialList(PreferentialBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(String user_id);
    }

    abstract class Presenter extends BasePresenter<PreferentialListContract.View, PreferentialListContract.Model> {
        public abstract void getPreferentialList(String user_id,MultipleStatusView multipleStatusView);
    }
}
