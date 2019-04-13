package com.dsige.belcen.mvp.contract;

import com.dsige.belcen.mvp.base.BasePresenter;
import com.dsige.belcen.mvp.base.BaseView;
import com.dsige.belcen.mvp.model.Cliente;

public interface RegisterContract {

    interface View extends BaseView {

        void setCliente(Cliente c);

        void goEditCliente();

        void showErrorMensaje(String mensaje);

        void close();
    }

    interface Presenter extends BasePresenter<View> {

        boolean validate(Cliente c);

        void getCliente(String cliente);

        void updateCliente(Cliente cliente);
    }
}
