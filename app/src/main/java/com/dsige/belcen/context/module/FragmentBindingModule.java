package com.dsige.belcen.context.module;

import com.dsige.belcen.ui.fragments.ClientFragment;
import com.dsige.belcen.ui.fragments.MapsFragment;
import com.dsige.belcen.ui.fragments.OrdersFragment;
import com.dsige.belcen.ui.fragments.ProductsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract ClientFragment provideClientFragment();

    @ContributesAndroidInjector
    abstract MapsFragment provideMapsFragment();

    @ContributesAndroidInjector
    abstract OrdersFragment provideOrdersFragment();

    @ContributesAndroidInjector
    abstract ProductsFragment provideProductsFragment();
}
