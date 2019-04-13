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
import com.dsige.belcen.mvp.model.Cliente;
import com.dsige.belcen.mvp.model.MenuPrincipal;
import com.dsige.belcen.ui.adapters.DialogSpinnerAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegisterClientActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
        ButterKnife.bind(this);
        bindUI();
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

        String tipo = Objects.requireNonNull(editTextTipo.getText()).toString().trim();
        String documento = Objects.requireNonNull(editTextDocumento.getText()).toString().trim();
        String nombre = Objects.requireNonNull(editTextNombre.getText()).toString().trim();
        String pago = Objects.requireNonNull(editTextPago.getText()).toString().trim();
        String departamento = Objects.requireNonNull(editTextDepartamento.getText()).toString().trim();
        String distrito = Objects.requireNonNull(editTextDistrito.getText()).toString().trim();
        String direccion = Objects.requireNonNull(editTextDireccion.getText()).toString().trim();
        String telefono = Objects.requireNonNull(editTextTelefono.getText()).toString().trim();
        String email = Objects.requireNonNull(editTextEmail.getText()).toString().trim();
        String visita = Objects.requireNonNull(editTextVisita.getText()).toString().trim();

        if (!tipo.isEmpty()) {
            if (!documento.isEmpty()) {
                if (!nombre.isEmpty()) {
                    if (!pago.isEmpty()) {
                        if (!departamento.isEmpty()) {
                            if (!distrito.isEmpty()) {
                                if (!direccion.isEmpty()) {
                                    if (!telefono.isEmpty()) {
                                        if (!email.isEmpty()) {
                                            if (!visita.isEmpty()) {
                                                Cliente c = new Cliente(tipo,
                                                        documento,
                                                        nombre,
                                                        pago,
                                                        departamento,
                                                        distrito,
                                                        direccion,
                                                        telefono,
                                                        email,
                                                        visita, 1);
                                                String cliente = new Gson().toJson(c);
                                                setResult(Permission.CLIENTE_INSERT_REQUEST,
                                                        new Intent().putExtra("cliente", cliente));
                                                finish();
                                            } else {
                                                Util.snackBarMensaje(getWindow().getDecorView(), "Ingrese Visita");
                                            }
                                        } else {
                                            Util.snackBarMensaje(getWindow().getDecorView(), "Ingrese Email");
                                        }
                                    } else {
                                        Util.snackBarMensaje(getWindow().getDecorView(), "Ingrese Telefono");
                                    }
                                } else {
                                    Util.snackBarMensaje(getWindow().getDecorView(), "Ingrese Direccion");
                                }
                            } else {
                                Util.snackBarMensaje(getWindow().getDecorView(), "Ingrese Distrito");
                            }
                        } else {
                            Util.snackBarMensaje(getWindow().getDecorView(), "Ingrese Departamento");
                        }
                    } else {
                        Util.snackBarMensaje(getWindow().getDecorView(), "Ingrese Pago");
                    }
                } else {
                    Util.snackBarMensaje(getWindow().getDecorView(), "Ingrese Nombre");
                }
            } else {
                Util.snackBarMensaje(getWindow().getDecorView(), "Ingrese Documento");
            }
        } else {
            Util.snackBarMensaje(getWindow().getDecorView(), "Ingrese Tipo");
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
}