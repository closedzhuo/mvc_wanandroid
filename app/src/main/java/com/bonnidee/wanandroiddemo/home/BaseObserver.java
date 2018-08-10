package com.bonnidee.wanandroiddemo.home;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
        onFinal();
    }

    protected abstract void onFinal();

    protected abstract void onSuccess(T t);

    @Override
    public void onError(Throwable e) {
        onFail(e);
        onFinal();
    }

    protected abstract void onFail(Throwable e);

    @Override
    public void onComplete() {

    }
}
