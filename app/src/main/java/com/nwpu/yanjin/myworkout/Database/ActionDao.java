package com.nwpu.yanjin.myworkout.Database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ActionDao {
    @Insert
    void InsertActions(Action... actions);

    @Update
    void UpdateActions(Action... actions);

    @Delete
    void DeleteActions(Action... actions);

    @Query("SELECT * FROM `action` ORDER BY actionName DESC")
    LiveData<List<Action>> getAllActionsLive();

}
