package com.dsige.belcen.context.components;

import android.app.Application;

import com.dsige.belcen.context.App;
import com.dsige.belcen.context.module.ActivityBindingModule;
import com.dsige.belcen.context.module.ApplicationModule;
import com.dsige.belcen.context.module.DataBaseModule;
import com.dsige.belcen.context.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ApplicationModule.class,
        DataBaseModule.class,
        RetrofitModule.class})
public interface ApplicationComponent extends AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}