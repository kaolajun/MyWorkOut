package com.nwpu.yanjin.myworkout;

public class Userfunction {
    private String functionName;
    private String functionIntroduce;
    private int id;
    public Userfunction(String functionName, String functionIntroduce,int id) {
        this.functionName = functionName;
        this.functionIntroduce = functionIntroduce;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionIntroduce() {
        return functionIntroduce;
    }

    public void setFunctionIntroduce(String functionIntroduce) {
        this.functionIntroduce = functionIntroduce;
    }
}
