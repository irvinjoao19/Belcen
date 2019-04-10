package com.dsige.belcen.context.room;

import android.app.Application;

import com.dsige.belcen.context.repository.RoomRepository;
import com.dsige.belcen.mvp.model.Categoria;
import com.dsige.belcen.mvp.model.Cliente;
import com.dsige.belcen.mvp.model.Pedido;
import com.dsige.belcen.mvp.model.Producto;
import com.dsige.belcen.mvp.model.Usuario;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.Completable;

public class RoomViewModel extends AndroidViewModel {

    private RoomRepository roomRepository;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        roomRepository = new RoomRepository(application);
    }

    public void CloseRoom() {
        roomRepository.closeRoom();
    }

    //TODO: Usuario

    public void insertUser(Usuario user) {
        roomRepository.insertUser(user);
    }

    public LiveData<Usuario> getUsuario() {
        return roomRepository.getUsuario();
    }

    public Completable deleteUser(Usuario user) {
        return roomRepository.deleteUser(user);
    }

    //TODO: Cliente

    public Completable insertClient(Cliente c) {
        return roomRepository.insertClient(c);
    }

    public Completable updateClient(Cliente c) {
        return roomRepository.updateClient(c);
    }

    public LiveData<List<Cliente>> getCliente() {
        return roomRepository.getCliente();
    }

    public Completable deleteCliente(Cliente c) {
        return roomRepository.deleteCliente(c);
    }

    //TODO : Producto

    public void insertProducto(Producto p) {
        roomRepository.insertProducto(p);
    }

    public LiveData<List<Producto>> getProducto() {
        return roomRepository.getProducto();
    }

    public Completable deleteProducto(Producto p) {
        return roomRepository.deleteProducto(p);
    }

    public void updateProducto(Producto p) {
        roomRepository.updateProducto(p);
    }

    //TODO : Categoria

    public void insertCategoria(Categoria c) {
        roomRepository.insertCategoria(c);
    }

    public LiveData<List<Categoria>> getCategoria() {
        return roomRepository.getCategoria();
    }

    public Completable deleteCategoria(Categoria c) {
        return roomRepository.deleteCategoria(c);
    }

    //TODO : Pedido

    public void insertPedido(Pedido p) {
        roomRepository.insertPedido(p);
    }

    public LiveData<List<Pedido>> getPedido() {
        return roomRepository.getPedido();
    }

    public Completable deletePedido(Pedido p) {
        return roomRepository.deletePedido(p);
    }

    public Completable updatePedido(Pedido p) {
        return roomRepository.updatePedido(p);
    }
}