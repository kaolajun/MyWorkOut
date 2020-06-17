package com.nwpu.yanjin.myworkout.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Action {
//    @PrimaryKey(autoGenerate = true)
//    private int id;

    @PrimaryKey
    @NonNull
    private String actionName;

    @ColumnInfo(name = "muscle")
    private String muscle;

    @ColumnInfo(name = "partOfBody")
    private String partOfBody;

    @ColumnInfo(name = "aerobicTime")
    private String aerobicTime;

    public Action(String actionName, String muscle, String partOfBody) {
        this.actionName = actionName;
        this.muscle = muscle;
        this.partOfBody = partOfBody;
    }



//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getAerobicTime() {
        return aerobicTime;
    }

    public void setAerobicTime(String aerobicTime) {
        this.aerobicTime = aerobicTime;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getPartOfBody() {
        return partOfBody;
    }

    public void setPartOfBody(String partOfBody) {
        this.partOfBody = partOfBody;
    }
}
