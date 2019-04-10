package com.dsige.belcen.mvp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Cliente {

    @PrimaryKey(autoGenerate = true)
    private int clienteId;
    private String tipo;
    private String documento;
    private String nombre;
    private String pago;
    private String departamento;
    private String distrito;
    private String direccion;
    private String telefono;
    private String email;
    private String fechaVisita;
    private int estado;

    public Cliente() {
    }

    @Ignore
    public Cliente(String tipo, String documento, String nombre, String pago, String departamento, String distrito, String direccion, String telefono, String email, String fechaVisita, int estado) {
        this.tipo = tipo;
        this.documento = documento;
        this.nombre = nombre;
        this.pago = pago;
        this.departamento = departamento;
        this.distrito = distrito;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaVisita = fechaVisita;
        this.estado = estado;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(String fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}