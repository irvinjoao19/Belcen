package com.dsige.belcen.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsige.belcen.R;
import com.dsige.belcen.mvp.model.Producto;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.ViewHolder> {

    private List<Producto> productos = Collections.emptyList();
    private OnItemClickListener listener;

    public PedidoAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void addItems(List<Producto> productosList) {
        productos = productosList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pedidos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(productos.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewNombre)
        TextView textViewNombre;
        @BindView(R.id.textViewCodigo)
        TextView textViewCodigo;
        @BindView(R.id.textViewStock)
        TextView textViewStock;
        @BindView(R.id.imageViewEdit)
        ImageView imageViewEdit;
        @BindView(R.id.editTextCantidad)
        EditText editTextCantidad;
        @BindView(R.id.imageViewNegative)
        ImageView imageViewNegative;
        @BindView(R.id.imageViewPositive)
        ImageView imageViewPositive;
        @BindView(R.id.textViewPrecio)
        TextView textViewPrecio;
        @BindView(R.id.textViewSubTotal)
        TextView textViewSubTotal;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Producto p, OnItemClickListener listener) {
            textViewNombre.setText(p.getNombre());
            textViewCodigo.setText(p.getCodigo());
            textViewPrecio.setText(String.format("S/. %s", p.getPrecioCompra()));
            textViewStock.setText(String.format("%s PZA", p.getStockMinimo()));

            editTextCantidad.setText(String.format("%s", p.getUnidadMedida()));
            textViewSubTotal.setText(String.format("Subtotal S/. %s", p.getUnidadMedida() * p.getPrecioCompra()));

            imageViewEdit.setOnClickListener(v -> listener.onItemClick(p, v, editTextCantidad, getAdapterPosition()));
            imageViewNegative.setOnClickListener(v -> listener.onItemClick(p, v, editTextCantidad, getAdapterPosition()));
            imageViewPositive.setOnClickListener(v -> listener.onItemClick(p, v, editTextCantidad, getAdapterPosition()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Producto p, View v, EditText e, int position);
//        void onLongClick(Cliente p, View v, int position);
    }
}
