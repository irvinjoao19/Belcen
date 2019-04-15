package com.dsige.belcen.context.repository;

import android.content.Context;
import android.util.Log;

import com.dsige.belcen.context.room.AppDataBase;
import com.dsige.belcen.helper.Util;
import com.dsige.belcen.mvp.model.Cliente;
import com.dsige.belcen.mvp.model.Pedido;
import com.dsige.belcen.mvp.model.Producto;
import com.dsige.belcen.mvp.model.Usuario;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AppRepoImp implements AppRepository {

    private ApiService apiService;
    private AppDataBase dataBase;

    public AppRepoImp(ApiService apiService, AppDataBase dataBase) {
        this.apiService = apiService;
        this.dataBase = dataBase;
    }

    @Override
    public Observable<Usuario> getUsuario(String usuario, String password) {
        Usuario u = new Usuario(usuario, password);
        String json = new Gson().toJson(u);
        Log.i("TAG", json);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return apiService.getUser(body);
    }

    @Override
    public void insertUser(Usuario u) {
        Completable.fromAction(() -> dataBase.userDao().insertUserTask(u))
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

    @Override
    public LiveData<Usuario> getUser() {
        return dataBase.userDao().getUserTask();
    }

    @Override
    public Completable deleteUser() {
        return Completable.fromAction(() -> dataBase.userDao().deleteAllTask());
    }

    @Override
    public LiveData<List<Cliente>> populateClientes() {
        return dataBase.clienteDao().getClienteTask();
    }

    @Override
    public Completable insertCliente(Cliente c) {
        return Completable.fromAction(() -> dataBase.clienteDao().insertClienteTask(c));
    }

    @Override
    public Completable updateCliente(Cliente c) {
        return Completable.fromAction(() -> dataBase.clienteDao().updateClienteTask(c));
    }

    @Override
    public LiveData<Cliente> getClienteById(int id) {
        return dataBase.clienteDao().getClienteTaskById(id);
    }

    @Override
    public LiveData<List<Producto>> populateProducto() {
        return dataBase.productoDao().getProductoTask();
    }

    @Override
    public void generateProducto() {
        Completable completable = Completable.fromAction(() -> {
            dataBase.productoDao().deleteAll();
            dataBase.pedidoDao().deleteAll();
            List<Producto> productos = new ArrayList<>();
            for (int i = 0; i <= 10; i++) {
                Producto p = new Producto();
                p.setProductoId(i);
                p.setCategoriaId(i);
                p.setCodigo("1234567");
                p.setCodigoBarra("1234567");
                p.setNombre(String.format("Mantequilla%s", i));
                p.setDescripcion(String.format("Descripcion%s", i));
                p.setPrecioCompra(10.5);
                p.setPrecioVenta(11);
                p.setUnidadMedida(0);
                p.setAbreviaturaProducto(String.format("MA%s", i));
                p.setUrlFoto(String.format("mantequilla%s.jpg", i));
                p.setPeso(100);
                p.setStockMinimo(10);
                p.setEstado(1);
                p.setFecha(Util.getFechaActual());
                p.setSubTotal(0);
                dataBase.productoDao().insertProductoTask(p);
                productos.add(p);
            }
            Pedido p = new Pedido(1, 1, 0, productos);
            dataBase.pedidoDao().insertPedidoTask(p);
        });
        completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });

    }

    @Override
    public void updateProducto(Producto p) {
        Completable.fromAction(() -> dataBase.productoDao().updateProductoTask(p))
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

    @Override
    public void closeRoom() {
        dataBase.close();
    }
}
