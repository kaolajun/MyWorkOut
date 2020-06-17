package com.nwpu.yanjin.myworkout.Database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface WeightDao {
    @Insert
    void InsertWeights(Weight... weights);

    @Update
    void UpdateWeights(Weight... weights);

//    @Update
    @Delete
    void DeleteWeights(Weight... weights);

    @Query("SELECT * FROM WEIGHT ORDER BY DATE DESC")
    LiveData<List<Weight>> getAllWeightLive();
}
