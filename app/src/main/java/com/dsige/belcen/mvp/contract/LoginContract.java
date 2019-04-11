package com.dsige.belcen.mvp.contract;

public interface LoginContract {

    interface View {
        String getUser();

        String getPassword();

        void showInputError(String mensaje);

        void goMainActivity();
    }

    interface Presenter {
        void setView(LoginContract.View view);

        void loginButtonClicked();
    }

    interface Model {

    }
}
