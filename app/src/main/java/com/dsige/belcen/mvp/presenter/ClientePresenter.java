package com.dsige.belcen.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.dsige.belcen.context.repository.AppRepository;
import com.dsige.belcen.helper.Util;
import com.dsige.belcen.mvp.contract.ClienteContract;
import com.dsige.belcen.mvp.model.Cliente;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClientePresenter implements ClienteContract.Presenter {

    private ClienteContract.View view;
    private AppRepository appRepository;

    public ClientePresenter(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    public void detachView(ClienteContract.View view) {
        this.view = null;
    }

    @Override
    public void attachView(ClienteContract.View view) {
        this.view = view;
    }

    @Override
    public void destroy() {
//        appRepository.closeRoom();
    }

    @Override
    public void insertCliente(Context context, Cliente c) {

        Completable completable = appRepository.insertCliente(c);
        completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Util.toastMensaje(context, "Cliente Agregado");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", e.toString());
                    }
                });

    }

    @Override
    public void addRegisterCliente() {
        view.goRegisterCliente();
    }

    @Override
    public void populateCliente() {
        appRepository.populateClientes().observeForever(clientes -> view.setClientes(clientes));
    }

    @Override
    public void startFileCliente(Cliente c) {
        view.goFileCliente(c);
    }

    @Override
    public void startMaps() {
        view.goMaps();
    }
}
