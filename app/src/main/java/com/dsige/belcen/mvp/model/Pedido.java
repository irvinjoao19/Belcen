package com.dsige.belcen.mvp.model;

import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Pedido {

    @PrimaryKey(autoGenerate = true)
    private int pedidoId;
    private int usuarioId;
    private double total;
    private List<Producto> productos;

    public Pedido() {
    }

    @Ignore
    public Pedido(int pedidoId, int usuarioId, double total, List<Producto> productos) {
        this.pedidoId = pedidoId;
        this.usuarioId = usuarioId;
        this.total = total;
        this.productos = productos;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
