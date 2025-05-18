package com.Turtles.Time_off_Manager_BackEnd.web.transfer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CreateTimeOffRequest {
    @NotNull
    private LocalDate start;
    @NotNull
    private LocalDate end;
    @NotNull
    private int type;
    @NotNull
    private int status;
    private String description;
    private CreateUserRequest createUserRequest;

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
    public void setUser(CreateUserRequest createUserRequest) {this.createUserRequest = createUserRequest;}
    public CreateUserRequest getUser(){ return this.createUserRequest;}
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description=description;}
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    @Override
    public boolean equals(Object o){
        if (o == null || this.getClass() != o.getClass()){
            return false;
        }
        CreateTimeOffRequest that=(CreateTimeOffRequest)o;
        return this.start.equals(that.start) && this.end.equals(that.end) && this.description.equals(that.description);
    }
}
