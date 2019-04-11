package com.dsige.belcen.ui.activities;

import androidx.appcompat.app.AlertDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;

import com.dsige.belcen.R;
import com.dsige.belcen.helper.Util;
import com.dsige.belcen.mvp.contract.LoginContract;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import javax.inject.Inject;

public class LoginActivity extends DaggerAppCompatActivity implements LoginContract.View {

    @Inject
    LoginContract.Presenter presenter;

    @BindView(R.id.editTextUser)
    TextInputEditText editTextUser;
    @BindView(R.id.editTextPass)
    TextInputEditText editTextPass;
    @BindView(R.id.buttonEnviar)
    MaterialButton buttonEnviar;

    AlertDialog.Builder builder;
    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @OnClick(R.id.buttonEnviar)
    public void submit(View view) {
        switch (view.getId()) {
            case R.id.buttonEnviar:
                presenter.loginButtonClicked();
                break;
        }
    }

    @Override
    public String getUser() {
        return Objects.requireNonNull(editTextUser.getText()).toString().trim();
    }

    @Override
    public String getPassword() {
        return Objects.requireNonNull(editTextPass.getText()).toString().trim();
    }

    @Override
    public void showInputError(String mensaje) {
        Util.snackBarMensaje(getWindow().getDecorView(), mensaje);
    }

    @Override
    public void loadLogin() {
        builder = new AlertDialog.Builder(new ContextThemeWrapper(LoginActivity.this, R.style.AppTheme));
        @SuppressLint("InflateParams") View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_login, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void closeLoadLogin() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void goMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}