package com.nwpu.yanjin.myworkout.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ActionWithDate.class},version = 1,exportSchema = false)
public abstract class ActionWithDateDatabase extends RoomDatabase {
    private static ActionWithDateDatabase INSTANCE;

    public static synchronized ActionWithDateDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),ActionWithDateDatabase.class,"ActionWithDateDatabase")
                    .build();

        }
        return INSTANCE;
    }

    public abstract ActionWithDateDao getActionWithDateDao();

}
