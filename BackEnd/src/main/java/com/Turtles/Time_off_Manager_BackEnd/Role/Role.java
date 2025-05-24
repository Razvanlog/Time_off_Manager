package com.Turtles.Time_off_Manager_BackEnd.Role;
import java.util.Map;
public enum Role {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    EMPLOYEE("EMPLOYEE");
    private final String label;
//    private final int rightLevel;
    Role(String label) {
        this.label = label;
//        this.rightLevel = rightLevel;
    }
    public String getLabel() {return label;}
//    public int getRightLevel() {return rightLevel;}
    public boolean isAdmin(){return this == ADMIN;}
    public boolean isManager(){return this == MANAGER;}
    public boolean isEmployee(){return this == EMPLOYEE;}
    @Override
    public String toString() {
        return label;
    }
}
