package com.dsige.belcen.context.room;

import android.content.Context;

import com.dsige.belcen.context.dao.CategoriaDao;
import com.dsige.belcen.context.dao.ClienteDao;
import com.dsige.belcen.context.dao.ProductoDao;
import com.dsige.belcen.context.dao.UserDao;
import com.dsige.belcen.helper.Util;
import com.dsige.belcen.model.Categoria;
import com.dsige.belcen.model.Cliente;
import com.dsige.belcen.model.Producto;
import com.dsige.belcen.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Database(entities = {
        Usuario.class,
        Cliente.class,
        Producto.class,
        Categoria.class
//        Migracion.class,
//        Cliente.class,
//        Personal.class,
//        EstadoTicket.class,
//        StatusTicket.class,
//        SapiaRegistro.class,
//        SapiaRegistroDetalle.class
},
        version = 1,
        exportSchema = false)
@TypeConverters({Converts.class})
public abstract class AppDataBase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract ClienteDao clienteDao();

    public abstract ProductoDao productoDao();

    public abstract CategoriaDao categoriaDao();

    private static volatile AppDataBase INSTANCE;

    public static AppDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "sapia_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     * <p>
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            Generar();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static void Generar() {
        ProductoDao productoDao = INSTANCE.productoDao();
        CategoriaDao categoriaDao = INSTANCE.categoriaDao();
        Completable completable = Completable.fromAction(() -> {

            categoriaDao.deleteAll();
            productoDao.deleteAll();

            for (int i = 0; i <= 10; i++) {

                Producto p = new Producto();
                p.setProductoId(i);
                p.setCategoriaId(i);
                p.setCodigo("1234567");
                p.setCodigoBarra("1234567");
                p.setNombre(String.format("Mantequilla%s", i));
                p.setDescripcion(String.format("Descripcion%s", i));
                p.setPrecioCompra(10.5);
                p.setPrecioVenta(11);
                p.setAbreviaturaProducto(String.format("MA%s", i));
                p.setUnidadMedida(10);
                p.setUrlFoto(String.format("mantequilla%s.jpg", i));
                p.setPeso(100);
                p.setStockMinimo(10);
                p.setEstado(1);
                p.setFecha(Util.getFechaActual());

                List<Producto> productos = new ArrayList<>();
                productos.add(p);

                Categoria a = new Categoria(String.format("Nombre%s", i), productos,i);

                categoriaDao.insertCategoriaTask(a);

                productoDao.insertProductoTask(p);
            }
        });
        completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }


}