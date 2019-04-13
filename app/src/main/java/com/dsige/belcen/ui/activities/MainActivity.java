package com.dsige.belcen.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.dsige.belcen.R;
import com.dsige.belcen.mvp.contract.MainContract;
import com.dsige.belcen.mvp.model.Usuario;
import com.dsige.belcen.ui.adapters.HeaderViewHolder;
import com.dsige.belcen.ui.fragments.ClientFragment;

import com.dsige.belcen.ui.fragments.MapsFragment;
import com.dsige.belcen.ui.fragments.OrdersFragment;
import com.dsige.belcen.ui.fragments.ProductsFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

import javax.inject.Inject;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;

    @Inject
    MainContract.Presenter presenter;

    HeaderViewHolder headerViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.attachView(this);
        presenter.getUserExist();
        bindUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
        presenter.destroy();
    }

    private void bindUI() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        fragmentByDefault();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clientes:
                changeFragment(ClientFragment.newInstance("", ""), item.getTitle().toString());
                break;
            case R.id.productos:
                changeFragment(ProductsFragment.newInstance("", ""), item.getTitle().toString());
                break;
            case R.id.pedidos:
                changeFragment(OrdersFragment.newInstance("", ""), item.getTitle().toString());
                break;
            case R.id.mapas:
                changeFragment(MapsFragment.newInstance("", ""), item.getTitle().toString());
                break;
            case R.id.logout:
                presenter.logout();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment fragment, String title) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    private void fragmentByDefault() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, ClientFragment.newInstance("", ""))
                .commit();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Cliente");
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void getUser(Usuario u) {
        View header = navigationView.getHeaderView(0);
        headerViewHolder = new HeaderViewHolder(header);
        headerViewHolder.textViewName.setText(u.getNombre());
        headerViewHolder.textViewEmail.setText(u.getEmail());
    }

    @Override
    public void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void closeSession() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}