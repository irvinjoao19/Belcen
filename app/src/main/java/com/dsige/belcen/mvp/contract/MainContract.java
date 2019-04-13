package com.dsige.belcen.mvp.contract;

import com.dsige.belcen.mvp.base.BasePresenter;
import com.dsige.belcen.mvp.base.BaseView;
import com.dsige.belcen.mvp.model.Usuario;

public interface MainContract {

    interface View extends BaseView {

        void getUser(Usuario usuario);

        void goLogin();

        void closeSession();
    }

    interface Presenter extends BasePresenter<View> {
        void getUserExist();

        void logout();
    }
}
