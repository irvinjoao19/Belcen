package com.dsige.belcen.context.dao;

import com.dsige.belcen.model.Cliente;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertClienteTask(Cliente c);

    @Delete
    void deleteClienteTask(Cliente c);

    @Query("SELECT * FROM Cliente")
    LiveData<List<Cliente>> getClienteTask();

    @Query("SELECT * FROM Cliente WHERE clienteId =:id")
    LiveData<Cliente> getClienteById(int id);
}