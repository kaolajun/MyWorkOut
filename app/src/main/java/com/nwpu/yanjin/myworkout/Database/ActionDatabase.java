package com.nwpu.yanjin.myworkout.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Action.class},version = 1,exportSchema = false)
public abstract class ActionDatabase extends RoomDatabase {

    private static ActionDatabase INSTANCE;
    //双重校验锁
    public static ActionDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized(ActionDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ActionDatabase.class, "actionDatabase")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract ActionDao getActionDao();


}
