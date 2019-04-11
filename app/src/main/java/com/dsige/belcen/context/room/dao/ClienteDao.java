package com.dsige.belcen.context.room.dao;

import com.dsige.belcen.mvp.model.Cliente;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertClienteTask(Cliente c);

    @Update
    void updateClienteTask(Cliente c);

    @Delete
    void deleteClienteTask(Cliente c);

    @Query("SELECT * FROM Cliente")
    LiveData<List<Cliente>> getClienteTask();

    @Query("SELECT * FROM Cliente WHERE clienteId =:id")
    LiveData<Cliente> getClienteById(int id);
}