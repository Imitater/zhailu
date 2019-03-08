package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.ConvertGoodsBean;
import com.mouqukeji.hmdeer.bean.DetailIntegralBean;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface ExperienceCardContract {
    interface View extends IBaseView {
        void detailIntegral(DetailIntegralBean bean);
        void convertGoods(ConvertGoodsBean bean);
        void isEnought();
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<DetailIntegralBean>> detailIntegral(String good_id);
        Observable<BaseHttpResponse<ConvertGoodsBean>> convertGoods(String goods_id,String user_id);
     }

    abstract class Presenter extends BasePresenter<ExperienceCardContract.View, ExperienceCardContract.Model> {
        public abstract void detailIntegral(String user_id ,MultipleStatusView multipleStatusView);
        public abstract void convertGoods(String good_id ,String user_id,MultipleStatusView multipleStatusView);
     }
}
