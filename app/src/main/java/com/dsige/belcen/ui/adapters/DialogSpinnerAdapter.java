package com.dsige.belcen.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsige.belcen.R;
import com.dsige.belcen.model.MenuPrincipal;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogSpinnerAdapter extends RecyclerView.Adapter<DialogSpinnerAdapter.ViewHolder> {

    private List<MenuPrincipal> menus;
    private OnItemClickListener listener;

    public DialogSpinnerAdapter(List<MenuPrincipal> menus, OnItemClickListener listener) {
        this.menus = menus;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_spinner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bind(menus.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewTitulo)
        TextView textViewTitulo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(MenuPrincipal m, OnItemClickListener listener) {
            textViewTitulo.setText(m.getTitle());
            itemView.setOnClickListener(view -> listener.onItemClick(m, view, getAdapterPosition()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MenuPrincipal m, View v, int position);
    }
}