package com.dsige.belcen.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dsige.belcen.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

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
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonEnviar)
    public void submit(View view) {
        switch (view.getId()) {
            case R.id.buttonEnviar:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}

