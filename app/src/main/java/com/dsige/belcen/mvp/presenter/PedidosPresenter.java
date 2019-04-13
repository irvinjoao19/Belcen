package com.dsige.belcen.mvp.presenter;

import com.dsige.belcen.context.repository.AppRepository;
import com.dsige.belcen.mvp.contract.PedidosContract;
import com.dsige.belcen.mvp.model.Producto;

import java.util.List;

import androidx.lifecycle.Observer;

public class PedidosPresenter implements PedidosContract.Presenter {

    private PedidosContract.View view;
    private AppRepository appRepository;

    public PedidosPresenter(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    public void generateProducto() {
        appRepository.generateProducto();
    }

    @Override
    public void populateProducto() {
        appRepository.populateProducto().observeForever(productos -> view.setProductos(productos));
    }

    @Override
    public void updateProducto(Producto p) {
        appRepository.updateProducto(p);
    }

    @Override
    public void detachView(PedidosContract.View view) {
        this.view = null;
    }

    @Override
    public void attachView(PedidosContract.View view) {
        this.view = view;
    }

    @Override
    public void destroy() {
//        appRepository.closeRoom();
    }
}
