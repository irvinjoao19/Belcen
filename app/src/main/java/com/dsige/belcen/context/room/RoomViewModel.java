package com.dsige.belcen.context.room;

import android.app.Application;

import com.dsige.belcen.context.repository.RoomRepository;
import com.dsige.belcen.model.Cliente;
import com.dsige.belcen.model.Usuario;

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

    public void insertUser(Usuario user) {
        roomRepository.insertUser(user);
    }

    public LiveData<Usuario> getUsuario() {
        return roomRepository.getUsuario();
    }

    public Completable deleteUser(Usuario user) {
        return roomRepository.deleteUser(user);
    }

    public void insertClient(Cliente c) {
        roomRepository.insertClient(c);
    }

    public LiveData<List<Cliente>> getCliente() {
        return roomRepository.getCliente();
    }

    public Completable deleteCliente(Cliente c) {
        return roomRepository.deleteCliente(c);
    }

}