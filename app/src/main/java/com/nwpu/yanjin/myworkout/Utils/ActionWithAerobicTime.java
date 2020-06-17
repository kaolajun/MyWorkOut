package com.nwpu.yanjin.myworkout.Utils;

import com.nwpu.yanjin.myworkout.Database.Action;

public class ActionWithAerobicTime {
    private Action action;
    private int AerobicTime;

    public ActionWithAerobicTime(Action action, int aerobicTime) {
        this.action = action;
        AerobicTime = aerobicTime;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getAerobicTime() {
        return AerobicTime;
    }

    public void setAerobicTime(int aerobicTime) {
        AerobicTime = aerobicTime;
    }
}
