package com.dsige.belcen.mvp.presenter;

import com.dsige.belcen.context.repository.AppRepository;
import com.dsige.belcen.mvp.contract.MainContract;

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
        view.closeSession();
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
