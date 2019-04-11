package com.dsige.belcen.context.retrofit;

import com.dsige.belcen.mvp.model.Usuario;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers("Cache-Control: no-cache")
    @POST("Sapia/GetUsuario")
    Observable<Usuario> getUser(@Body RequestBody body);
}