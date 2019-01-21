package com.mouqu.zhailu.zhailu.net;


import com.mouqu.zhailu.zhailu.bean.ErrorBean;

public interface BaseObserverListener<T> {
    void onSuccess(T result);
    void onComplete();
    void onError(Throwable e);
    void onBusinessError(ErrorBean errorBean);
    void onReLoad();
    void onBeing();
}
