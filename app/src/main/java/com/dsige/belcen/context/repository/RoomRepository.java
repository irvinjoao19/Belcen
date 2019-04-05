package com.dsige.belcen.context.repository;

import android.app.Application;
import android.util.Log;

import com.dsige.belcen.context.room.AppDataBase;
import com.dsige.belcen.model.Cliente;
import com.dsige.belcen.model.Usuario;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RoomRepository {

    private AppDataBase appDataBase;

    public RoomRepository(Application application) {
        this.appDataBase = AppDataBase.getDatabase(application);
    }

    public void closeRoom() {
        appDataBase.close();
    }

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

    public void insertClient(Cliente c) {
        Completable.fromAction(() -> appDataBase.clienteDao().insertClienteTask(c))
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

    public LiveData<List<Cliente>> getCliente() {
        return appDataBase.clienteDao().getClienteTask();
    }

    public Completable deleteCliente(Cliente c) {
        return Completable.fromAction(() -> appDataBase.clienteDao().deleteClienteTask(c));
    }
}