package com.dsige.belcen.context.repository;

import android.content.Context;

import com.dsige.belcen.mvp.model.Cliente;
import com.dsige.belcen.mvp.model.Producto;
import com.dsige.belcen.mvp.model.Usuario;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.Observable;

public interface AppRepository {

    // TODO : USUARIO

    Observable<Usuario> getUsuario(String usuario, String password);

    void insertUser(Usuario u);

    LiveData<Usuario> getUser();

    Completable deleteUser();

    // TODO : CLIENTE

    LiveData<List<Cliente>> populateClientes();

    Completable insertCliente(Cliente c);

    Completable updateCliente(Cliente c);

    LiveData<Cliente> getClienteById(int id);

    //TODO : Producto

    LiveData<List<Producto>> populateProducto();

    void generateProducto();

    void updateProducto(Producto p);

    void closeRoom();


}
