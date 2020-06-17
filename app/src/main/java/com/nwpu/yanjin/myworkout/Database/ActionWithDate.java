package com.nwpu.yanjin.myworkout.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ActionWithDate {

//    //date的格式
//    date = String.valueOf(year)+String.valueOf(month)+String.valueOf(dayOfMonth);
    @PrimaryKey
    @NonNull
    private String date;

    @ColumnInfo(name = "ActionsWithDate")
    private String actionsWithDateName;

    public ActionWithDate(@NonNull String date, String actionsWithDateName) {
        this.date = date;
        this.actionsWithDateName = actionsWithDateName;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public String getActionsWithDateName() {
        return actionsWithDateName;
    }

    public void setActionsWithDateName(String actionsWithDateName) {
        this.actionsWithDateName = actionsWithDateName;
    }
}
