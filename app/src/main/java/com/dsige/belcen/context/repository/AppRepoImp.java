package com.dsige.belcen.context.repository;

import android.util.Log;

import com.dsige.belcen.context.retrofit.ApiService;
import com.dsige.belcen.context.room.AppDataBase;
import com.dsige.belcen.mvp.model.Usuario;
import com.google.gson.Gson;

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
}
