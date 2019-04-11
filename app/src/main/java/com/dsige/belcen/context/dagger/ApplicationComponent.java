package com.dsige.belcen.context.dagger;

import com.dsige.belcen.context.dagger.module.LoginModule;
import com.dsige.belcen.ui.activities.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity target);

}
