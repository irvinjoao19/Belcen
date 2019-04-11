package com.dsige.belcen.mvp.presenter;

import com.dsige.belcen.mvp.contract.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    @Override
    public void setView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void loginButtonClicked() {
        if (view != null) {
            if (!view.getUser().isEmpty()) {
                if (!view.getPassword().isEmpty()) {
                    view.goMainActivity();
                } else {
                    view.showInputError("Ingrese Password");
                }
            } else {
                view.showInputError("Ingrese Usuario");
            }
        }

    }
}
