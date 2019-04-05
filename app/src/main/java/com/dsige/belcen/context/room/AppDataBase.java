package com.dsige.belcen.context.room;

import android.content.Context;

import com.dsige.belcen.context.dao.ClienteDao;
import com.dsige.belcen.context.dao.UserDao;
import com.dsige.belcen.model.Cliente;
import com.dsige.belcen.model.Usuario;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {
        Usuario.class,
        Cliente.class
//        Migracion.class,
//        Cliente.class,
//        Personal.class,
//        EstadoTicket.class,
//        StatusTicket.class,
//        SapiaRegistro.class,
//        SapiaRegistroDetalle.class
},
        version = 3,
        exportSchema = false)
//@TypeConverters({Converts.class})
public abstract class AppDataBase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract ClienteDao clienteDao();

    private static volatile AppDataBase INSTANCE;

    public static AppDataBase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "sapia_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}