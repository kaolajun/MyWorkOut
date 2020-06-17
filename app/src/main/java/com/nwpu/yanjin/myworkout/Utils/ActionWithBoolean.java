package com.nwpu.yanjin.myworkout.Utils;

import com.nwpu.yanjin.myworkout.Database.Action;

public class ActionWithBoolean {
    private Action action;
    private Boolean isSelected;

    public ActionWithBoolean(Action action, Boolean isSelected) {
        this.action = action;
        this.isSelected = isSelected;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
