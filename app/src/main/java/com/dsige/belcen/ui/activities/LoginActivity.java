package com.dsige.belcen.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dsige.belcen.R;
import com.dsige.belcen.context.dagger.App;
import com.dsige.belcen.helper.Util;
import com.dsige.belcen.mvp.contract.LoginContract;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @Inject
    LoginContract.Presenter presenter;

    @BindView(R.id.editTextUser)
    TextInputEditText editTextUser;
    @BindView(R.id.editTextPass)
    TextInputEditText editTextPass;
    @BindView(R.id.buttonEnviar)
    MaterialButton buttonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((App) getApplication()).getComponent().inject(this);
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
                //    startActivity(new Intent(this, MainActivity.class));
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
    public void goMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }


}

