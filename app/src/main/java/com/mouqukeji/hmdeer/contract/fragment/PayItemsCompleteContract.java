package com.mouqukeji.hmdeer.contract.fragment;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.EvaluationBean;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.contract.activity.MainContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;
import retrofit2.http.Query;

public interface PayItemsCompleteContract {
    interface View extends IBaseView {
    }

    interface Model extends BaseModel {
    }

    abstract class Presenter extends BasePresenter<PayItemsCompleteContract.View, PayItemsCompleteContract.Model> {
    }
}
