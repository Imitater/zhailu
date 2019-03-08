package com.mouqukeji.hmdeer.presenter.activity;


  import com.mouqukeji.hmdeer.base.RxObserverListener;
  import com.mouqukeji.hmdeer.bean.AddPostBean;
  import com.mouqukeji.hmdeer.bean.VipListBean;
  import com.mouqukeji.hmdeer.contract.activity.ApplyForContract;
  import com.mouqukeji.hmdeer.net.RetrofitManager;
  import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class ApplyForPresenter extends  ApplyForContract.Presenter{


  @Override
  public void getMoney(String name, String school, String grade, String mobile, String superiority, MultipleStatusView multipleStatusView) {
    rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.addPost(name,school,grade,mobile,superiority), new RxObserverListener<AddPostBean>(mView) {
      @Override
      public void onSuccess(AddPostBean result) {
        mView.addPost(result);
      }
    }));
  }
}

