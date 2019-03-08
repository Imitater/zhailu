package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.AddPostBean;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface ApplyForContract {
    interface View extends IBaseView {
        void addPost(AddPostBean bean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AddPostBean>> addPost( String name,String school,String grade,String mobile,String superiority);
     }

    abstract class Presenter extends BasePresenter<ApplyForContract.View, ApplyForContract.Model> {
        public abstract void getMoney( String name,String school,String grade,String mobile,String superiority,MultipleStatusView multipleStatusView);
     }
}
