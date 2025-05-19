package com.Turtles.Time_off_Manager_BackEnd.LeaveType;

import java.util.Arrays;

public enum LeaveType {
    VACATION("Vacation Days", 1.5, 20),
    SICK("Sick Leave", 1.0, 10),
    PERSONAL("Personal Time Off", 0.0, 5);

    private final String label;
    private final double accrualRate;
    private final int maxBalance;

    LeaveType(String label, double accrualRate, int maxBalance) {
        this.label = label;
        this.accrualRate = accrualRate;
        this.maxBalance = maxBalance;
    }

    public String getLabel() {
        return label;
    }

    public double getAccrualRate() {
        return accrualRate;
    }

    public int getMaxBalance() {
        return maxBalance;
    }
    public static LeaveType fromString(String text) {
        for (LeaveType b : LeaveType.values()) {
            if (b.name().equalsIgnoreCase(text) || b.getLabel().equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("No leave type constant with text '" + text + "' found. Valid names are: " + Arrays.toString(LeaveType.values()) + " or their labels.");
    }

}