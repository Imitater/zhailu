package com.mouqukeji.hmdeer.net;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;


public class RxManager {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    /**
     * 添加observer
     * @param observer
     */
    public void addObserver(DisposableObserver observer){
        if(observer!=null){
            compositeDisposable.add(observer);
        }
    }

    public void clear(){
        compositeDisposable.dispose();
    }

}
