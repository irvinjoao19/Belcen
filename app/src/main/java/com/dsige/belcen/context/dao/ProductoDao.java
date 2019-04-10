package com.dsige.belcen.context.dao;

import com.dsige.belcen.mvp.model.Producto;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProductoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProductoTask(Producto p);

    @Delete
    void deleteProductoTask(Producto p);

    @Update
    void updateProductoTask(Producto p);

    @Query("SELECT * FROM Producto")
    LiveData<List<Producto>> getProductoTask();

    @Query("SELECT * FROM Producto WHERE productoId =:id")
    LiveData<Producto> getProductoById(int id);

    @Query("DELETE FROM Producto")
    void deleteAll();
}