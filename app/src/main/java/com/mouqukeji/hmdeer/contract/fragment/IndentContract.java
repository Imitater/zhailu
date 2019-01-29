package com.mouqukeji.hmdeer.contract.fragment;



import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;

public interface IndentContract {
    interface View extends IBaseView {
    }

    interface Model extends BaseModel {
    }

    abstract class Presenter extends BasePresenter<View, Model> {
    }
}
