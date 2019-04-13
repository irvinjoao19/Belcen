package com.dsige.belcen.mvp.presenter;

import android.util.Log;

import com.dsige.belcen.context.repository.AppRepository;
import com.dsige.belcen.helper.Util;
import com.dsige.belcen.mvp.contract.MainContract;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private AppRepository appRepository;

    public MainPresenter(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    public void getUserExist() {
        appRepository.getUser().observeForever(usuario -> {
            if (usuario != null) {
                view.getUser(usuario);
            } else {
                view.goLogin();
            }
        });
    }

    @Override
    public void logout() {
        Completable completable = appRepository.deleteUser();
        completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        view.closeSession();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setError(e.toString());
                        Log.i("TAG", e.toString());
                    }
                });
    }

    @Override
    public void detachView(MainContract.View view) {
        this.view = null;
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void destroy() {
//        appRepository.closeRoom();
    }
}
