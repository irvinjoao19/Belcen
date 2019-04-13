package com.dsige.belcen.ui.activities;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dsige.belcen.R;
import com.dsige.belcen.mvp.contract.FileClienteContract;
import com.dsige.belcen.mvp.model.Cliente;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.Objects;

import javax.inject.Inject;

public class FileClientActivity extends DaggerAppCompatActivity implements FileClienteContract.View {

    @OnClick({R.id.fab})
    public void submit(View view) {
        switch (view.getId()) {
            case R.id.fab:
                presenter.startEditCliente(cliente);
                break;
        }
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.textViewTipo)
    TextView textViewTipo;
    @BindView(R.id.textViewDocumento)
    TextView textViewDocumento;
    @BindView(R.id.textViewNombre)
    TextView textViewNombre;
    @BindView(R.id.textViewPago)
    TextView textViewPago;
    @BindView(R.id.textViewDepartamento)
    TextView textViewDepartamento;
    @BindView(R.id.textViewDistrito)
    TextView textViewDistrito;
    @BindView(R.id.textViewDireccion)
    TextView textViewDireccion;
    @BindView(R.id.textViewPhone)
    TextView textViewPhone;
    @BindView(R.id.textViewEmail)
    TextView textViewEmail;
    @BindView(R.id.textViewVisita)
    TextView textViewVisita;

    @Inject
    FileClienteContract.Presenter presenter;

    Cliente cliente;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_client);
        ButterKnife.bind(this);
        presenter.attachView(this);
        cliente = new Cliente();
        bindUI();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String json = bundle.getString("cliente");
            presenter.getCliente(json);
        }
    }

    private void bindUI() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Cliente");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    public void setCliente(Cliente c) {
        this.cliente = c;
        textViewTipo.setText(c.getTipo());
        textViewDocumento.setText(c.getDocumento());
        textViewNombre.setText(c.getNombre());
        textViewPago.setText(c.getPago());
        textViewDepartamento.setText(c.getDepartamento());
        textViewDistrito.setText(c.getDistrito());
        textViewDireccion.setText(c.getDireccion());
        textViewPhone.setText(c.getTelefono());
        textViewEmail.setText(c.getEmail());
        textViewVisita.setText(c.getFechaVisita());
    }

    @Override
    public void goEditCliente(Cliente c) {
        String json = new Gson().toJson(c);
        startActivity(new Intent(this, RegisterClientActivity.class).putExtra("cliente", json));
    }

    @Override
    public void goMaps() {

    }
}
