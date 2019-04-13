package com.dsige.belcen.ui.activities;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.dsige.belcen.R;
import com.dsige.belcen.mvp.contract.FileClienteContract;
import com.dsige.belcen.mvp.model.Cliente;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import javax.inject.Inject;

public class FileClientActivity extends DaggerAppCompatActivity implements FileClienteContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

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
    public void goEditCliente() {

    }

    @Override
    public void goMaps() {

    }
}
