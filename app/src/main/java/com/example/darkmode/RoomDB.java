package com.example.darkmode;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = MainData.class,version = 1,exportSchema = false)
public abstract class  RoomDB extends RoomDatabase {
    private static RoomDB db;
    private static final String DATABASE_NAME="database";
    public synchronized static RoomDB getInstance(Context context){
        if (db==null){
            db= Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,DATABASE_NAME)
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return db;
    }
    public abstract MainDao mainDao();
}
