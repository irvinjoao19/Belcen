package com.dsige.belcen.ui.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dsige.belcen.R;
import com.dsige.belcen.helper.Permission;
import com.dsige.belcen.helper.Util;
import com.dsige.belcen.mvp.contract.RegisterContract;
import com.dsige.belcen.mvp.model.Cliente;
import com.dsige.belcen.mvp.model.MenuPrincipal;
import com.dsige.belcen.ui.adapters.DialogSpinnerAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class RegisterClientActivity extends DaggerAppCompatActivity implements RegisterContract.View {

    @OnClick({R.id.editTextTipo, R.id.editTextVisita})
    void submit(View view) {
        switch (view.getId()) {
            case R.id.editTextTipo:
                dialogSpinner();
                break;
            case R.id.editTextVisita:
                Util.getDateDialog(this, view, editTextVisita);
                break;
        }
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editTextTipo)
    TextInputEditText editTextTipo;
    @BindView(R.id.editTextDocumento)
    TextInputEditText editTextDocumento;
    @BindView(R.id.editTextNombre)
    TextInputEditText editTextNombre;
    @BindView(R.id.editTextPago)
    TextInputEditText editTextPago;
    @BindView(R.id.editTextDepartamento)
    TextInputEditText editTextDepartamento;
    @BindView(R.id.editTextDistrito)
    TextInputEditText editTextDistrito;
    @BindView(R.id.editTextDireccion)
    TextInputEditText editTextDireccion;
    @BindView(R.id.editTextTelefono)
    TextInputEditText editTextTelefono;
    @BindView(R.id.editTextEmail)
    TextInputEditText editTextEmail;
    @BindView(R.id.editTextVisita)
    TextInputEditText editTextVisita;

    @Inject
    RegisterContract.Presenter presenter;

    Cliente cliente;
    boolean modoUpdate = false;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
        ButterKnife.bind(this);
        presenter.attachView(this);
        bindUI();
        cliente = new Cliente();
        Bundle b = getIntent().getExtras();
        if (b != null) {
            presenter.getCliente(b.getString("cliente"));
            modoUpdate = true;
        }
    }

    private void bindUI() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Registrar Cliente");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
        editTextVisita.setText(Util.getFecha());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.register) {
            formRegisterCliente();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void formRegisterCliente() {

        cliente.setTipo(Objects.requireNonNull(editTextTipo.getText()).toString().trim());
        cliente.setDocumento(Objects.requireNonNull(editTextDocumento.getText()).toString().trim());
        cliente.setNombre(Objects.requireNonNull(editTextNombre.getText()).toString().trim());
        cliente.setPago(Objects.requireNonNull(editTextPago.getText()).toString().trim());
        cliente.setDepartamento(Objects.requireNonNull(editTextDepartamento.getText()).toString().trim());
        cliente.setDistrito(Objects.requireNonNull(editTextDistrito.getText()).toString().trim());
        cliente.setDireccion(Objects.requireNonNull(editTextDireccion.getText()).toString().trim());
        cliente.setTelefono(Objects.requireNonNull(editTextTelefono.getText()).toString().trim());
        cliente.setEmail(Objects.requireNonNull(editTextEmail.getText()).toString().trim());
        cliente.setFechaVisita(Objects.requireNonNull(editTextVisita.getText()).toString().trim());
        cliente.setEstado(1);

        if (presenter.validate(cliente)) {
            if (modoUpdate) {
                presenter.updateCliente(cliente);
            } else {
                String json = new Gson().toJson(cliente);
                setResult(Permission.CLIENTE_INSERT_REQUEST,
                        new Intent().putExtra("cliente", json));
                finish();
            }
        }
    }

    private void dialogSpinner() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
        @SuppressLint("InflateParams") View view = LayoutInflater.from(this).inflate(R.layout.dialog_spinner, null);
        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        builder.setView(view);
        AlertDialog dialogSpinner = builder.create();
        dialogSpinner.setCanceledOnTouchOutside(false);
        dialogSpinner.show();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);

        textViewTitle.setText(String.format("%s", "Tipo"));
        List<MenuPrincipal> menus = new ArrayList<>();
        menus.add(new MenuPrincipal(1, "Natural"));
        menus.add(new MenuPrincipal(2, "Juridico"));
        DialogSpinnerAdapter dialogSpinnerAdapter = new DialogSpinnerAdapter(menus, (m, v, position) -> {
            editTextTipo.setText(m.getTitle());
            dialogSpinner.dismiss();
        });
        recyclerView.setAdapter(dialogSpinnerAdapter);
    }

    @Override
    public void setCliente(Cliente c) {
        cliente = c;
        editTextTipo.setText(c.getTipo());
        editTextDocumento.setText(c.getDocumento());
        editTextNombre.setText(c.getNombre());
        editTextPago.setText(c.getPago());
        editTextDepartamento.setText(c.getDepartamento());
        editTextDistrito.setText(c.getDistrito());
        editTextDireccion.setText(c.getDireccion());
        editTextTelefono.setText(c.getTelefono());
        editTextEmail.setText(c.getEmail());
        editTextVisita.setText(c.getFechaVisita());
    }

    @Override
    public void goEditCliente() {

    }

    @Override
    public void showErrorMensaje(String mensaje) {
        Util.snackBarMensaje(getWindow().getDecorView(), mensaje);
    }

    @Override
    public void close() {
        finish();
    }

}