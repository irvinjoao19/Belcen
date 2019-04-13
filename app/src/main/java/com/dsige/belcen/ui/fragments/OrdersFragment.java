package com.dsige.belcen.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsige.belcen.R;
import com.dsige.belcen.helper.Util;
import com.dsige.belcen.mvp.contract.PedidosContract;
import com.dsige.belcen.mvp.model.Producto;
import com.dsige.belcen.ui.adapters.PedidoAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

public class OrdersFragment extends DaggerFragment implements PedidosContract.View {

    @Inject
    PedidosContract.Presenter presenter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.textViewTotal)
    TextView textViewTotal;
    private Unbinder unbinder;
    private PedidoAdapter pedidoAdapter;

    public OrdersFragment() {
        // Required empty public constructor
    }

    public static OrdersFragment newInstance(String param1, String param2) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_settings).setVisible(false).setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pedidos) {
            Util.toastMensaje(getContext(), "HOLA");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
        presenter.destroy();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        unbinder = ButterKnife.bind(this, view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        pedidoAdapter = new PedidoAdapter((p, v, e, position) -> {
            switch (v.getId()) {
                case R.id.imageViewPositive:
                    double suma = Double.parseDouble(e.getText().toString());
                    String sTotal = String.valueOf(suma + 1);
                    e.setText(sTotal);
                    double nPositive = Double.parseDouble(sTotal);
                    p.setUnidadMedida(nPositive);
                    p.setSubTotal(nPositive * p.getPrecioCompra());
                    presenter.updateProducto(p);
                    break;
                case R.id.imageViewNegative:
                    double resta = Double.parseDouble(e.getText().toString());
                    if (resta != 0) {
                        String rTotal = String.valueOf(resta - 1);
                        e.setText(rTotal);
                        double nNegative = Double.parseDouble(rTotal);
                        p.setUnidadMedida(nNegative);
                        p.setSubTotal(nNegative * p.getPrecioCompra());
                        presenter.updateProducto(p);
                    }
                    break;
                case R.id.editTextCantidad:
                    break;
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pedidoAdapter);
        presenter.populateProducto();
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
    public void setProductos(List<Producto> productos) {
        double total = 0;
        for (Producto p : productos) {
            total = total + p.getSubTotal();
        }
        textViewTotal.setText(String.format("Total S/. %s", total));
        pedidoAdapter.addItems(productos);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
