package com.dsige.belcen.mvp.presenter;

import android.util.Log;

import com.dsige.belcen.context.repository.AppRepository;
import com.dsige.belcen.mvp.contract.LoginContract;
import com.dsige.belcen.mvp.model.Usuario;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private AppRepository appRepository;

    public LoginPresenter(AppRepository appRepository) {
        this.appRepository = appRepository;
    }


    @Override
    public void loginButtonClicked() {
        if (view != null) {
            if (!view.getUser().isEmpty()) {
                if (!view.getPassword().isEmpty()) {
                    view.loadLogin();
                    getUsuario();
                } else {
                    view.showInputError("Ingrese Password");
                }
            } else {
                view.showInputError("Ingrese Usuario");
            }
        }
    }

    private void getUsuario() {
        String mensaje[] = {null};
        Observable<Usuario> userObservable = appRepository.getUsuario(view.getUser(), view.getPassword());
        userObservable.subscribeOn(Schedulers.io())
                .delay(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Usuario>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Usuario user) {
                        mensaje[0] = user.getMensaje();
                        if (mensaje[0].equals("Go")) {
                            appRepository.insertUser(user);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.closeLoadLogin();
//                        if (e instanceof HttpException) {
//                            ResponseBody body = ((HttpException) e).response().errorBody();
//                            Converter<ResponseBody, MessageError> errorConverter =new Retrofit().responseBodyConverter(MessageError.class, new Annotation[0])
//                            try {
//                                MessageError error = errorConverter.convert(Objects.requireNonNull(body));
//                                if (error.getMessage().contains("Usuario")) {
//                                    view.showInputError("Usuario No existe");
//                                } else {
//                                    view.showInputError(error.getMessage());
//                                }
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                            }
//                        } else {
                        view.showInputError(e.toString());
//                        }
                        Log.i("TAG", e.toString());
                    }

                    @Override
                    public void onComplete() {
                        switch (mensaje[0]) {
                            case "Go":
                                view.closeLoadLogin();
                                view.goMainActivity();
                                break;
                            case "Pass":
                                view.closeLoadLogin();
                                view.showInputError("Password Incorrecto");
                                break;
                        }
                    }
                });

    }

    @Override
    public void detachView(LoginContract.View view) {
        this.view = null;
    }

    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void destroy() {
//        appRepository.closeRoom();
    }
}
