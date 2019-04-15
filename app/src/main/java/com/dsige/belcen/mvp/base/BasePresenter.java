package com.dsige.belcen.mvp.base;


public interface BasePresenter<V extends BaseView>  {
    void detachView(V view);

    void attachView(V view);

    void destroy();
}


