package com.dsige.belcen.mvp.contract;

public interface LoginContract {

    interface View {
        String getUser();

        String getPassword();

        void showInputError(String mensaje);

        void loadLogin();

        void closeLoadLogin();

        void goMainActivity();
    }

    interface Presenter {
        void setView(LoginContract.View view);

        void loginButtonClicked();
    }

    interface Model {

    }
}
