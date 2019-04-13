package com.dsige.belcen.mvp.contract;

import com.dsige.belcen.mvp.base.BasePresenter;
import com.dsige.belcen.mvp.base.BaseView;
import com.dsige.belcen.mvp.model.Producto;

import java.util.List;

public interface PedidosContract {

    interface View extends BaseView {
        void setProductos(List<Producto> productos);
    }

    interface Presenter extends BasePresenter<View> {

        void generateProducto();

        void populateProducto();

        void updateProducto(Producto p);
    }

}
