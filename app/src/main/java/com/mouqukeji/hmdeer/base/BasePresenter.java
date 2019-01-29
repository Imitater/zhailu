package com.mouqukeji.hmdeer.base;


import com.mouqukeji.hmdeer.net.RxManager;

public abstract class BasePresenter<V, M > {
    public M mModel;
    public V mView;
    public RxManager rxManager = new RxManager();

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
    }
    /**
     * 防止内存泄漏
     */
    public void onDestroy() {
        rxManager.clear();
        rxManager = null;
        mView = null;
    }
}
