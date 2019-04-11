package com.dsige.belcen.context.room.dao;

import com.dsige.belcen.mvp.model.Pedido;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PedidoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPedidoTask(Pedido p);

    @Delete
    void deletePedidoTask(Pedido c);

    @Update
    void updatePedidoTask(Pedido c);

    @Query("SELECT * FROM Pedido")
    LiveData<List<Pedido>> getPedidoTask();

    @Query("SELECT * FROM Pedido WHERE pedidoId =:id")
    LiveData<Pedido> getPedidoById(int id);

    @Query("DELETE FROM Pedido")
    void deleteAll();

}
