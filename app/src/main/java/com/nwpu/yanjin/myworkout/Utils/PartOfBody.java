package com.nwpu.yanjin.myworkout.Utils;

public enum PartOfBody {
    AEROBIC("有氧"),
    CHEST("胸"),
    BACK("背"),
    LEG("腿"),
    SHOULDER("肩"),
    BICEPS("二头"),
    TRICEPS("三头"),
    ABS("腹肌");
    private final String partOfBodyName;

    PartOfBody(String partOfBodyName) {
        this.partOfBodyName = partOfBodyName;
    }

    public String getPartOfBodyName() {
        return partOfBodyName;
    }
}
