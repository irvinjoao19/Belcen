package com.dsige.belcen.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.dsige.belcen.R;
import com.dsige.belcen.helper.Permission;
import com.dsige.belcen.helper.Util;
import com.dsige.belcen.mvp.model.Cliente;
import com.dsige.belcen.ui.activities.FileClientActivity;
import com.dsige.belcen.ui.activities.MapsActivity;
import com.dsige.belcen.ui.activities.RegisterClientActivity;
import com.dsige.belcen.ui.adapters.ClienteAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClientFragment extends DaggerFragment {

    @OnClick(R.id.fab)
    void submit(View view) {
        switch (view.getId()) {
            case R.id.fab:
                startActivityForResult(new Intent(getContext(), RegisterClientActivity.class), Permission.CLIENTE_REQUEST);
                break;
        }
    }


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private Unbinder unbinder;
    private ClienteAdapter clienteAdapter;
//    private RoomViewModel roomViewModel;

    public ClientFragment() {

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.pedidos).setVisible(false).setEnabled(false);
        menu.findItem(R.id.add).setVisible(false).setEnabled(false);
    }

    public static ClientFragment newInstance(String param1, String param2) {
        ClientFragment fragment = new ClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        roomViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_client, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        clienteAdapter = new ClienteAdapter((c, v, position) -> {
            switch (v.getId()) {
                case R.id.imageViewMap:
                    startActivity(new Intent(getContext(), MapsActivity.class));
                    break;
                default:
                    startActivity(new Intent(getContext(), FileClientActivity.class));
                    break;
            }
//            String cliente = new Gson().toJson(c);
//                startActivityForResult(new Intent(PersonalActivity.this, RegisterPersonalActivity.class)
//                                .putExtra("personal", personal)
//                                .putExtra("update", true)
//                        , Permission.PERSONAL_REQUEST);
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(clienteAdapter);
//        LiveData<List<Cliente>> clienteData = roomViewModel.getCliente();
//        clienteData.observe(this, clients -> clienteAdapter.addItems(clients));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Permission.CLIENTE_REQUEST) {
//            if (resultCode == Permission.CLIENTE_INSERT_REQUEST) {
//                String cliente = data.getStringExtra("cliente");
//                Cliente c = new Gson().fromJson(cliente, Cliente.class);
//                Completable completable = roomViewModel.insertClient(c);
//                completable.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new CompletableObserver() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                Util.toastMensaje(getContext(), "Cliente Agregado");
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.i("TAG", e.toString());
//                            }
//                        });
////                aprobacionAdapter.clearByPosition(position);
//            } else if (resultCode == Permission.CLIENTE_UPDATE_REQUEST) {
//                String personal = data.getStringExtra("cliente");
//                Cliente c = new Gson().fromJson(personal, Cliente.class);
//                Completable completable = roomViewModel.updateClient(c);
//                completable.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new CompletableObserver() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                Util.toastMensaje(getContext(), "Personal Actualizado");
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.i("TAG", e.toString());
//                            }
//                        });
//            }
//        }
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
