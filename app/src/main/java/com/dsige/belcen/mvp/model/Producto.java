package com.dsige.belcen.mvp.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Producto implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int productoId;
    private int categoriaId;
    private String codigo;
    private String codigoBarra;
    private String nombre;
    private String descripcion;
    private double precioCompra;
    private double precioVenta;
    private String abreviaturaProducto;
    private double unidadMedida;
    private String urlFoto;
    private double peso;
    private int stockMinimo;
    private int estado;
    private double subTotal;
    private String fecha;

    public Producto() {
    }

    @Ignore
    public Producto(int productoId, int categoriaId, String codigo, String codigoBarra, String nombre, String descripcion, double precioCompra, double precioVenta, String abreviaturaProducto, double unidadMedida, String urlFoto, double peso, int stockMinimo, int estado, double subTotal, String fecha) {
        this.productoId = productoId;
        this.categoriaId = categoriaId;
        this.codigo = codigo;
        this.codigoBarra = codigoBarra;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.abreviaturaProducto = abreviaturaProducto;
        this.unidadMedida = unidadMedida;
        this.urlFoto = urlFoto;
        this.peso = peso;
        this.stockMinimo = stockMinimo;
        this.estado = estado;
        this.subTotal = subTotal;
        this.fecha = fecha;
    }


    protected Producto(Parcel in) {
        productoId = in.readInt();
        categoriaId = in.readInt();
        codigo = in.readString();
        codigoBarra = in.readString();
        nombre = in.readString();
        descripcion = in.readString();
        precioCompra = in.readDouble();
        precioVenta = in.readDouble();
        abreviaturaProducto = in.readString();
        unidadMedida = in.readDouble();
        urlFoto = in.readString();
        peso = in.readDouble();
        stockMinimo = in.readInt();
        estado = in.readInt();
        subTotal = in.readDouble();
        fecha = in.readString();
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productoId);
        dest.writeInt(categoriaId);
        dest.writeString(codigo);
        dest.writeString(codigoBarra);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeDouble(precioCompra);
        dest.writeDouble(precioVenta);
        dest.writeString(abreviaturaProducto);
        dest.writeDouble(unidadMedida);
        dest.writeString(urlFoto);
        dest.writeDouble(peso);
        dest.writeInt(stockMinimo);
        dest.writeInt(estado);
        dest.writeDouble(subTotal);
        dest.writeString(fecha);
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getAbreviaturaProducto() {
        return abreviaturaProducto;
    }

    public void setAbreviaturaProducto(String abreviaturaProducto) {
        this.abreviaturaProducto = abreviaturaProducto;
    }

    public double getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(double unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public static Creator<Producto> getCREATOR() {
        return CREATOR;
    }
}