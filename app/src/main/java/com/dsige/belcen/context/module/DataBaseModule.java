package com.dsige.belcen.context.module;

import android.app.Application;

import com.dsige.belcen.context.room.dao.CategoriaDao;
import com.dsige.belcen.context.room.AppDataBase;
import com.dsige.belcen.context.room.dao.ClienteDao;
import com.dsige.belcen.context.room.dao.PedidoDao;
import com.dsige.belcen.context.room.dao.ProductoDao;
import com.dsige.belcen.context.room.dao.UserDao;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {

    @Provides
    @Singleton
    AppDataBase provideRoomDatabase(Application application) {
        if (AppDataBase.INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (AppDataBase.INSTANCE == null) {
                    AppDataBase.INSTANCE = Room.databaseBuilder(application.getApplicationContext(),
                            AppDataBase.class, AppDataBase.DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return AppDataBase.INSTANCE;
    }

    @Provides
    CategoriaDao provideCategoriaDao(AppDataBase appDataBase) {
        return appDataBase.categoriaDao();
    }

    @Provides
    ClienteDao provideClienteDao(AppDataBase appDataBase) {
        return appDataBase.clienteDao();
    }

    @Provides
    PedidoDao providePedidoDao(AppDataBase appDataBase) {
        return appDataBase.pedidoDao();
    }

    @Provides
    ProductoDao provideProductoDao(AppDataBase appDataBase) {
        return appDataBase.productoDao();
    }

    @Provides
    UserDao provideUserDao(AppDataBase appDataBase) {
        return appDataBase.userDao();
    }
}