package com.dsige.belcen.context.module;

import com.dsige.belcen.ui.activities.FileClientActivity;
import com.dsige.belcen.ui.activities.LoginActivity;
import com.dsige.belcen.ui.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {FragmentBindingModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract FileClientActivity bindFileClientActivity();
}