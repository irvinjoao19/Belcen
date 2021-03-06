package com.dsige.belcen.mvp.presenter;

import com.dsige.belcen.context.repository.AppRepository;
import com.dsige.belcen.mvp.contract.FileClienteContract;
import com.dsige.belcen.mvp.model.Cliente;

public class FileClientePresenter implements FileClienteContract.Presenter {

    private FileClienteContract.View view;
    private AppRepository appRepository;

    public FileClientePresenter(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    public void getCliente(int id) {
        appRepository.getClienteById(id).observeForever(cliente -> view.setCliente(cliente));
    }

    @Override
    public void startEditCliente(Cliente c) {
        view.goEditCliente(c);
    }

    @Override
    public void detachView(FileClienteContract.View view) {
        this.view = null;
    }

    @Override
    public void attachView(FileClienteContract.View view) {
        this.view = view;
    }

    @Override
    public void destroy() {

    }
}
