package com.dsige.belcen.context.dao;

import com.dsige.belcen.model.Categoria;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CategoriaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategoriaTask(Categoria c);

    @Delete
    void deleteCategoriaTask(Categoria c);

    @Query("SELECT * FROM Categoria")
    LiveData<List<Categoria>> getCategoriaTask();

    @Query("SELECT * FROM Categoria WHERE categoriaId =:id")
    LiveData<Categoria> getCategoriaById(int id);

    @Query("DELETE FROM Categoria")
    void deleteAll();
}
