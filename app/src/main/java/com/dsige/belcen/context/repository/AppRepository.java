package com.dsige.belcen.context.repository;

import com.dsige.belcen.mvp.model.Usuario;

import io.reactivex.Observable;

public interface AppRepository {

    Observable<Usuario> getUsuario(String usuario, String password);

    void insertUser(Usuario u);
}
