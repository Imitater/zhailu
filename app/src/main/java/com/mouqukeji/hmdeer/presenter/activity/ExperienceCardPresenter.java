package com.mouqukeji.hmdeer.presenter.activity;



import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.ConvertGoodsBean;
import com.mouqukeji.hmdeer.bean.DetailIntegralBean;
import com.mouqukeji.hmdeer.contract.activity.ExperienceCardContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;


public class ExperienceCardPresenter extends  ExperienceCardContract.Presenter{

    @Override
    public void detailIntegral(String good_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.detailIntegral(good_id), new RxObserverListener<DetailIntegralBean>(mView) {
            @Override
            public void onSuccess(DetailIntegralBean result) {
                mView.detailIntegral(result);
            }
        }));
    }

    @Override
    public void convertGoods(String good_id,String user_id ,MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.convertGoods(good_id,user_id), new RxObserverListener<ConvertGoodsBean>(mView) {
            @Override
            public void onSuccess(ConvertGoodsBean result) {
                    mView.convertGoods(result);
            }

            @Override
            public void onReLoad() {
                super.onReLoad();
                mView.isEnought();
            }
        }));
    }
}

