package com.dsige.belcen.context.module;

import com.dsige.belcen.context.repository.AppRepository;
import com.dsige.belcen.mvp.contract.ClienteContract;
import com.dsige.belcen.mvp.contract.FileClienteContract;
import com.dsige.belcen.mvp.contract.LoginContract;
import com.dsige.belcen.mvp.contract.MainContract;
import com.dsige.belcen.mvp.contract.PedidosContract;
import com.dsige.belcen.mvp.contract.RegisterContract;
import com.dsige.belcen.mvp.presenter.ClientePresenter;
import com.dsige.belcen.mvp.presenter.FileClientePresenter;
import com.dsige.belcen.mvp.presenter.LoginPresenter;
import com.dsige.belcen.mvp.presenter.MainPresenter;
import com.dsige.belcen.mvp.presenter.PedidosPresenter;
import com.dsige.belcen.mvp.presenter.RegisterPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    LoginContract.Presenter providerLoginPresenter(AppRepository appRepository) {
        return new LoginPresenter(appRepository);
    }

    @Provides
    PedidosContract.Presenter providerPedidosPresenter(AppRepository appRepository) {
        return new PedidosPresenter(appRepository);
    }

    @Provides
    MainContract.Presenter providerMainPresenter(AppRepository appRepository) {
        return new MainPresenter(appRepository);
    }

    @Provides
    ClienteContract.Presenter providerClientePresenter(AppRepository appRepository) {
        return new ClientePresenter(appRepository);
    }

    @Provides
    FileClienteContract.Presenter providerFileClientePresenter(AppRepository appRepository) {
        return new FileClientePresenter(appRepository);
    }

    @Provides
    RegisterContract.Presenter providerRegisterPresenter(AppRepository appRepository) {
        return new RegisterPresenter(appRepository);
    }
}