package com.dsige.belcen.context.repository;

import android.app.Application;
import android.util.Log;

import com.dsige.belcen.context.room.AppDataBase;
import com.dsige.belcen.mvp.model.Categoria;
import com.dsige.belcen.mvp.model.Cliente;
import com.dsige.belcen.mvp.model.Pedido;
import com.dsige.belcen.mvp.model.Producto;
import com.dsige.belcen.mvp.model.Usuario;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RoomRepository {


    private AppDataBase appDataBase;

    public RoomRepository() {

    }

    public void closeRoom() {
        appDataBase.close();
    }

    //TODO : Usuario

    public void insertUser(Usuario user) {
        Completable.fromAction(() -> appDataBase.userDao().insertUserTask(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("TAG", "COMPLETADO");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", e.toString());
                    }
                });
    }

    public LiveData<Usuario> getUsuario() {
        return appDataBase.userDao().getUserTask();
    }

    public Completable deleteUser(Usuario user) {
        return Completable.fromAction(() -> appDataBase.userDao().deleteUserTask(user));
    }

    //TODO : Cliente

    public Completable insertClient(Cliente c) {
        return Completable.fromAction(() -> appDataBase.clienteDao().insertClienteTask(c));
    }

    public Completable updateClient(Cliente c) {
        return Completable.fromAction(() -> appDataBase.clienteDao().updateClienteTask(c));
    }

    public LiveData<List<Cliente>> getCliente() {
        return appDataBase.clienteDao().getClienteTask();
    }

    public Completable deleteCliente(Cliente c) {
        return Completable.fromAction(() -> appDataBase.clienteDao().deleteClienteTask(c));
    }

    //TODO : Producto

    public void insertProducto(Producto p) {
        Completable.fromAction(() -> appDataBase.productoDao().insertProductoTask(p))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("TAG", "COMPLETADO");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", e.toString());
                    }
                });
    }

    public LiveData<List<Producto>> getProducto() {
        return appDataBase.productoDao().getProductoTask();
    }

    public Completable deleteProducto(Producto p) {
        return Completable.fromAction(() -> appDataBase.productoDao().deleteProductoTask(p));
    }

    public void updateProducto(Producto p) {
        Completable.fromAction(() -> appDataBase.productoDao().updateProductoTask(p))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("TAG", "ACTUALIZADO");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", e.toString());
                    }
                });
    }

    //TODO : Categoria

    public void insertCategoria(Categoria c) {
        Completable.fromAction(() -> appDataBase.categoriaDao().insertCategoriaTask(c))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("TAG", "COMPLETADO");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", e.toString());
                    }
                });
    }

    public LiveData<List<Categoria>> getCategoria() {
        return appDataBase.categoriaDao().getCategoriaTask();
    }

    public Completable deleteCategoria(Categoria p) {
        return Completable.fromAction(() -> appDataBase.categoriaDao().deleteCategoriaTask(p));
    }

    //TODO : Pedido

    public void insertPedido(Pedido p) {
        Completable.fromAction(() -> appDataBase.pedidoDao().insertPedidoTask(p))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("TAG", "COMPLETADO");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", e.toString());
                    }
                });
    }

    public LiveData<List<Pedido>> getPedido() {
        return appDataBase.pedidoDao().getPedidoTask();
    }

    public Completable deletePedido(Pedido p) {
        return Completable.fromAction(() -> appDataBase.pedidoDao().deletePedidoTask(p));
    }

    public Completable updatePedido(Pedido p) {
        return Completable.fromAction(() -> appDataBase.pedidoDao().updatePedidoTask(p));
    }
}