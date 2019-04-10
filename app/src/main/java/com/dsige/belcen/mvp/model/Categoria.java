package com.dsige.belcen.mvp.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Categoria extends ExpandableGroup<Producto> {

    @PrimaryKey(autoGenerate = true)
    private int categoriaId;
    private String nombre;

    public Categoria(String title, List<Producto> items,int categoriaId) {
        super(title, items);
        this.categoriaId = categoriaId;
    }

//    private List<Producto> productos;
//    private List<Producto> productos;


    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

//    public List<Producto> getProductos() {
//        return productos;
//    }
//
//    public void setProductos(List<Producto> productos) {
//        this.productos = productos;
//    }
}