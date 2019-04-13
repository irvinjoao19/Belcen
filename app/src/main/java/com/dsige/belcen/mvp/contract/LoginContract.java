package com.dsige.belcen.mvp.contract;

import com.dsige.belcen.mvp.base.BasePresenter;
import com.dsige.belcen.mvp.base.BaseView;

public interface LoginContract {

    interface View extends BaseView {
        String getUser();

        String getPassword();

        void showInputError(String mensaje);

        void loadLogin();

        void closeLoadLogin();

        void goMainActivity();
    }

    interface Presenter  extends BasePresenter<View> {
        void loginButtonClicked();
    }

}
