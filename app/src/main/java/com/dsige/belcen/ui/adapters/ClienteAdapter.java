package com.dsige.belcen.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsige.belcen.R;
import com.dsige.belcen.mvp.model.Cliente;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {

    private List<Cliente> clients = Collections.emptyList();
    private OnItemClickListener listener;

    public ClienteAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void addItems(List<Cliente> clientsList) {
        clients = clientsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_client, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(clients.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewNombre)
        TextView textViewNombre;
        @BindView(R.id.textViewDocumento)
        TextView textViewDocumento;
        @BindView(R.id.textViewDireccion)
        TextView textViewDireccion;
        @BindView(R.id.textViewVisita)
        TextView textViewVisita;
        @BindView(R.id.imageViewMap)
        ImageView imageViewMap;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Cliente c, OnItemClickListener listener) {
            textViewNombre.setText(c.getNombre());
            textViewDocumento.setText(c.getDocumento());
            textViewDireccion.setText(c.getDireccion());
            textViewVisita.setText(String.format("Ultima Visita : %s", c.getFechaVisita()));
            itemView.setOnClickListener(v -> listener.onItemClick(c, v, getAdapterPosition()));
            imageViewMap.setOnClickListener(v -> listener.onItemClick(c, v, getAdapterPosition()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Cliente c, View v, int position);
//        void onLongClick(Cliente p, View v, int position);
    }
}
