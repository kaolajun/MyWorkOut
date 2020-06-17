package com.nwpu.yanjin.myworkout.Utils;

public class TodayActionDisplay {
    private String actionName;
    private int aerobicTime;

    public TodayActionDisplay(String actionName, int aerobicTime) {
        this.actionName = actionName;
        this.aerobicTime = aerobicTime;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getAerobicTime() {
        return aerobicTime;
    }

    public void setAerobicTime(int aerobicTime) {
        this.aerobicTime = aerobicTime;
    }
}
