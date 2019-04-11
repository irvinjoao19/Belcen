package com.dsige.belcen.context.dagger.module;

import com.dsige.belcen.mvp.contract.LoginContract;
import com.dsige.belcen.mvp.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    LoginContract.Presenter providerLoginPresenter() {
        return new LoginPresenter();
    }

}
