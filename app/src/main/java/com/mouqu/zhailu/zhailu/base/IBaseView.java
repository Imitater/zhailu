package com.mouqu.zhailu.zhailu.base;


  import com.mouqu.zhailu.zhailu.bean.ErrorBean;
  import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public interface IBaseView {
    /**
     * show loading message
     *
     * @param msg
     */
    void showLoading(MultipleStatusView multipleStatusView, String msg);

    /**
     * hide loading
     */
    void hideLoading();

    /**
     * show business error :网络异常及数据错误等异常情况
     */
    void showBusinessError(ErrorBean error);

    void showException(ErrorBean error);
}
