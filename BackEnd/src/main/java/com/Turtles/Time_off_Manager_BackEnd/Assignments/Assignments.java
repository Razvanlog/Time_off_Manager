package com.Turtles.Time_off_Manager_BackEnd.Assignments;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
public class Assignments {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column (name="role",nullable = false)
    private int role;
    void setRole(int role) {
        this.role = role;
    }
    int getRole(){
        return role;
    }
    @Column(name="user",nullable = false)
    private int user;
    void setUser(int user) {
        this.user = user;
    }
    int getUser(){
        return user;
    }
    @Column(name="project",nullable=false)
    private int project;
    void setProject(int project) {
        this.project = project;
    }
    int getProject(){
        return project;
    }
}
