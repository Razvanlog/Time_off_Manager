package com.Turtles.Time_off_Manager_BackEnd.web.transfer;

import java.util.List;

public class ProjectResponse {
    private String name;
    private UserResponse manager;
    private List<UserResponse> employees;
    public void setName(String a){
        this.name = a;
    }
    public String getName(){
        return this.name;
    }
    public void setManager(UserResponse a){
        this.manager = a;
    }
    public UserResponse getManager(){
        return this.manager;
    }
    public void setEmployees(List<UserResponse> a){
        this.employees = a;
    }
    public void addEmployee(UserResponse a){
        this.employees.add(a);
    }
    public void removeEmployee(UserResponse a){
        this.employees.remove(a);
    }
    public List<UserResponse> getEmployees(){
        return this.employees;
    }
    @Override
    public boolean equals(Object o){
        if (o==null || (this.getClass()!=o.getClass())) return false;
        ProjectResponse other = (ProjectResponse)o;
        return this.name.equals(other.name);
    }
}
