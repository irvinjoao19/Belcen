package com.dsige.belcen.context.room;

import com.dsige.belcen.context.room.dao.CategoriaDao;
import com.dsige.belcen.context.room.dao.ClienteDao;
import com.dsige.belcen.context.room.dao.PedidoDao;
import com.dsige.belcen.context.room.dao.ProductoDao;
import com.dsige.belcen.context.room.dao.UserDao;
import com.dsige.belcen.mvp.model.Categoria;
import com.dsige.belcen.mvp.model.Cliente;
import com.dsige.belcen.mvp.model.Pedido;
import com.dsige.belcen.mvp.model.Producto;
import com.dsige.belcen.mvp.model.Usuario;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {
        Usuario.class,
        Cliente.class,
        Producto.class,
        Categoria.class,
        Pedido.class},
        version = 1,
        exportSchema = false)
@TypeConverters({Converts.class})
public abstract class AppDataBase extends RoomDatabase {

    public static final String DB_NAME = "belcen_db";

    public abstract UserDao userDao();

    public abstract ClienteDao clienteDao();

    public abstract ProductoDao productoDao();

    public abstract CategoriaDao categoriaDao();

    public abstract PedidoDao pedidoDao();

    public static volatile AppDataBase INSTANCE;
//
//    public static AppDataBase getDatabase(Context context) {
//        if (INSTANCE == null) {
//            synchronized (AppDataBase.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            AppDataBase.class, "sapia_db")
//                            .fallbackToDestructiveMigration()
//                            .addCallback(sRoomDatabaseCallback)
//                            .build();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//            Generar();
//        }
//    };
//
//    /**
//     * Populate the database in the background.
//     * If you want to start with more words, just add them.
//     */
//    private static void Generar() {
//        ProductoDao productoDao = INSTANCE.productoDao();
//        PedidoDao pedidoDao = INSTANCE.pedidoDao();
//        Completable completable = Completable.fromAction(() -> {
//
//
//            productoDao.deleteAll();
//            pedidoDao.deleteAll();
//
//            List<Producto> productos = new ArrayList<>();
//
//            for (int i = 0; i <= 10; i++) {
//
//                Producto p = new Producto();
//                p.setProductoId(i);
//                p.setCategoriaId(i);
//                p.setCodigo("1234567");
//                p.setCodigoBarra("1234567");
//                p.setNombre(String.format("Mantequilla%s", i));
//                p.setDescripcion(String.format("Descripcion%s", i));
//                p.setPrecioCompra(10.5);
//                p.setPrecioVenta(11);
//                p.setUnidadMedida(0);
//                p.setAbreviaturaProducto(String.format("MA%s", i));
//                p.setUrlFoto(String.format("mantequilla%s.jpg", i));
//                p.setPeso(100);
//                p.setStockMinimo(10);
//                p.setEstado(1);
//                p.setFecha(Util.getFechaActual());
//                p.setSubTotal(0);
//                productoDao.insertProductoTask(p);
//                productos.add(p);
//
//            }
//
//            Pedido p = new Pedido(1, 1, 0, productos);
//            pedidoDao.insertPedidoTask(p);
//
//        });
//        completable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                });
//    }


}