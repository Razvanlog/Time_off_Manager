package com.Turtles.Time_off_Manager_BackEnd.web.transfer;

import java.time.LocalDate;

public class TimeOffRequestResponse {
    private String description;
    private LocalDate start;
    private LocalDate end;
    private UserResponse user;
    private int type;
    private int status;

    public LocalDate getStart() {
        return start;
    }
    public void setStart(LocalDate start) {
        this.start = start;
    }
    public LocalDate getEnd() {
        return end;
    }
    public void setEnd(LocalDate end) {
        this.end = end;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description=description;}
    public void setUser(UserResponse user) {this.user = user;}
    public UserResponse getUser() {return user;}
    public int getType() {return type;}
    public void setType(int type) {this.type = type;}
    public int getStatus() {return status;}
    public void setStatus(int status) {this.status=status;}
    @Override
    public boolean equals(Object o){
        if (o == null || this.getClass() != o.getClass()){
            return false;
        }
        TimeOffRequestResponse that=(TimeOffRequestResponse) o;
        return this.start.equals(that.start) && this.end.equals(that.end) && this.description.equals(that.description);
    }

    public void setLeaveType(String name) { this.type=type;}
    }

