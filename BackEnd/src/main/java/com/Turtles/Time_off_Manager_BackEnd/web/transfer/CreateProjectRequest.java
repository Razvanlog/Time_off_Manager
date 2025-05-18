package com.Turtles.Time_off_Manager_BackEnd.web.transfer;

import com.Turtles.Time_off_Manager_BackEnd.User.User;
import jakarta.validation.constraints.NotNull;
import java.util.List;
public class CreateProjectRequest {
    @NotNull
    private String name;
    private CreateUserRequest manager;
    private List<CreateUserRequest> employees;
    public void setName(String a){
        this.name = a;
    }
    public String getName(){
        return this.name;
    }
    public void setManager(CreateUserRequest a){
        this.manager = a;
    }
    public CreateUserRequest getManager(){
        return this.manager;
    }
    public void setEmployees(List<CreateUserRequest> a){
        this.employees = a;
    }
    public void addEmployee(CreateUserRequest a){
        this.employees.add(a);
    }
    public void removeEmployee(CreateUserRequest a){
        this.employees.remove(a);
    }
    public List<CreateUserRequest> getEmployees(){
        return this.employees;
    }
    @Override
    public boolean equals(Object o){
        if (o==null || (this.getClass()!=o.getClass())) return false;
        CreateProjectRequest other = (CreateProjectRequest) o;
        return this.name.equals(other.name);
    }
}
