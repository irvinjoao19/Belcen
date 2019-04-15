package com.dsige.belcen.mvp.contract;

import com.dsige.belcen.mvp.base.BasePresenter;
import com.dsige.belcen.mvp.base.BaseView;
import com.dsige.belcen.mvp.model.Cliente;

public interface FileClienteContract {

    interface View extends BaseView {
        void setCliente(Cliente c);

        void goEditCliente(Cliente c);

        void goMaps();
    }

    interface Presenter extends BasePresenter<View> {

        void getCliente(int id);

        void startEditCliente(Cliente c);

    }
}
