package com.nwpu.yanjin.myworkout.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Weight {
//    @PrimaryKey(autoGenerate = true)
//    private int id;

    @PrimaryKey
    @NonNull
    private String date;

    @ColumnInfo(name = "weight")
    private double weight;

    @ColumnInfo(name = "targetWeight")
    private double targetWeight;

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public Weight(String date, double weight) {
        this.date = date;
        this.weight = weight;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
