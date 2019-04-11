package com.dsige.belcen.context.module;

import com.dsige.belcen.context.repository.AppRepository;
import com.dsige.belcen.mvp.contract.LoginContract;
import com.dsige.belcen.mvp.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    LoginContract.Presenter providerLoginPresenter(AppRepository appRepository) {
        return new LoginPresenter(appRepository);
    }

}