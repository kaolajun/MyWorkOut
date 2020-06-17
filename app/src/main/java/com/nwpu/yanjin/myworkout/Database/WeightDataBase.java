package com.nwpu.yanjin.myworkout.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Weight.class},version = 2,exportSchema = false)
public abstract class WeightDataBase extends RoomDatabase {
    private static WeightDataBase INSTANCE;

    public static synchronized WeightDataBase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WeightDataBase.class,"WeightDataBase")
                    .addMigrations(MIGRATION_1_2)
                    .build();

        }
        return INSTANCE;
    }

    //自己写版本迁移（version1到2）
    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //底层操作，需要调用SQLite语句
            database.execSQL("ALTER TABLE weight ADD COLUMN targetWeight Double NOT NULL DEFAULT 0");
        }
    };

    public abstract WeightDao getWeightDao();

}
