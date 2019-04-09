package com.dsige.belcen.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsige.belcen.R;
import com.dsige.belcen.model.Categoria;
import com.dsige.belcen.model.Producto;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.Collections;
import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class ProductoAdapter extends ExpandableRecyclerViewAdapter<ProductoAdapter.GenreViewHolder, ProductoAdapter.ArtistViewHolder> {

    private OnItemClickListener listener;

    public ProductoAdapter(List<? extends ExpandableGroup> groups, OnItemClickListener listener) {
        super(groups);
        this.listener = listener;
    }

    @Override
    public GenreViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_genero, parent, false);
        return new GenreViewHolder(v);

    }

    @Override
    public ArtistViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_productos, parent, false);
        return new ArtistViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(ArtistViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        holder.bind((Producto) group.getItems().get(childIndex), listener);
    }

    @Override
    public void onBindGroupViewHolder(GenreViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGenreTitle(group);
    }

    class GenreViewHolder extends GroupViewHolder {

        private TextView textViewName;
        private ImageView imageViewArrow;

        GenreViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageViewArrow = itemView.findViewById(R.id.imageViewArrow);
        }

        void setGenreTitle(ExpandableGroup group) {
            textViewName.setText(group.getTitle());
        }

        @Override
        public void expand() {
            animateExpand();
        }

        @Override
        public void collapse() {
            animateCollapse();
        }

        private void animateExpand() {
            RotateAnimation rotate =
                    new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            imageViewArrow.setAnimation(rotate);
        }

        private void animateCollapse() {
            RotateAnimation rotate =
                    new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setFillAfter(true);
            imageViewArrow.setAnimation(rotate);
        }
    }

    class ArtistViewHolder extends ChildViewHolder {

        private TextView textViewName;

        ArtistViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
        }

        void bind(Producto p, OnItemClickListener listener) {
            textViewName.setText(p.getNombre());
            itemView.setOnClickListener(v -> listener.onItemClick(p, v, getAdapterPosition()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Producto p, View v, int position);
    }
}
