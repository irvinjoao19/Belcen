package com.dsige.belcen.mvp.contract;

import android.content.Context;

import com.dsige.belcen.mvp.base.BasePresenter;
import com.dsige.belcen.mvp.base.BaseView;
import com.dsige.belcen.mvp.model.Cliente;

import java.util.List;

public interface ClienteContract {

    interface View extends BaseView {
        void setClientes(List<Cliente> customers);

        void goRegisterCliente();

        void goMaps();

        void goFileCliente(Cliente c);
    }

    interface Presenter extends BasePresenter<View> {

        void insertCliente(Context context, Cliente c);

        void addRegisterCliente();

        void populateCliente();

        void startFileCliente(Cliente c);

        void startMaps();
    }

    interface OnItemClickListener {

        void onItemClick(Cliente c, android.view.View v, int position);

        void onLongClick(Cliente c, android.view.View v, int position);
    }

    interface DeleteListener {

        void setConfirm(boolean confirm, long personId);

    }

}
