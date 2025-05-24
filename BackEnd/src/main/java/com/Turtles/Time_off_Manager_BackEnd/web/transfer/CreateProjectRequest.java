package com.Turtles.Time_off_Manager_BackEnd.web.transfer;

import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public class CreateProjectRequest {
    @NotNull
    private String name;
    private String managerEmail;
    private List<String> employeesEmail;
    public void setName(String a){
        this.name = a;
    }
    public String getName(){
        return this.name;
    }
    public void setManager(String email){
        this.managerEmail = email;
    }
    public String getManager(){
        return this.managerEmail;
    }
    public void setEmployees(List<String> a){
        this.employeesEmail = a;
    }
    public void addEmployee(String email){
        this.employeesEmail.add(email);
    }
    public void removeEmployee(String a){
        this.employeesEmail.remove(a);
    }
    public List<String> getEmployees(){
        return this.employeesEmail;
    }
    @Override
    public boolean equals(Object o){
        if (o==null || (this.getClass()!=o.getClass())) return false;
        CreateProjectRequest other = (CreateProjectRequest) o;
        return this.name.equals(other.name);
    }
}
