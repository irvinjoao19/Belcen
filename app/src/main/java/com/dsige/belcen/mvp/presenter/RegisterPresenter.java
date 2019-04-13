package com.dsige.belcen.mvp.presenter;

import android.util.Log;

import com.dsige.belcen.context.repository.AppRepository;
import com.dsige.belcen.mvp.contract.RegisterContract;
import com.dsige.belcen.mvp.model.Cliente;
import com.google.gson.Gson;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View view;
    private AppRepository appRepository;

    public RegisterPresenter(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    public boolean validate(Cliente c) {

        if (c.getTipo().isEmpty()) {
            view.showErrorMensaje("Ingrese Tipo");
            return false;
        }
        if (c.getDocumento().isEmpty()) {
            view.showErrorMensaje("Ingrese Documento");
            return false;
        }
        if (c.getNombre().isEmpty()) {
            view.showErrorMensaje("Ingrese Nombre");
            return false;
        }
        if (c.getPago().isEmpty()) {
            view.showErrorMensaje("Ingrese Pago");
            return false;
        }
        if (c.getDepartamento().isEmpty()) {
            view.showErrorMensaje("Ingrese Departamento");
            return false;
        }
        if (c.getDistrito().isEmpty()) {
            view.showErrorMensaje("Ingrese Distrito");
            return false;
        }
        if (c.getDireccion().isEmpty()) {
            view.showErrorMensaje("Ingrese Dirección");
            return false;
        }
        if (c.getTelefono().isEmpty()) {
            view.showErrorMensaje("Ingrese Telefono");
            return false;
        }
        if (c.getEmail().isEmpty()) {
            view.showErrorMensaje("Ingrese Email");
            return false;
        }
        if (c.getFechaVisita().isEmpty()) {
            view.showErrorMensaje("Ingrese Fecha");
            return false;
        }

        return true;
    }

    @Override
    public void getCliente(String cliente) {
        Cliente c = new Gson().fromJson(cliente, Cliente.class);
        view.setCliente(c);
    }

    @Override
    public void updateCliente(Cliente cliente) {

        Completable completable = appRepository.updateCliente(cliente);
        completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        view.close();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", e.toString());
                    }
                });
    }

    @Override
    public void detachView(RegisterContract.View view) {
        this.view = null;
    }

    @Override
    public void attachView(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void destroy() {

    }
}
