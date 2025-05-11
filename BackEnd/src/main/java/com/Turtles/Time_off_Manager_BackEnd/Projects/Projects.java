package com.Turtles.Time_off_Manager_BackEnd.Projects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name",unique = true, nullable = false)
    private String name;
    public void setName(String a){
        this.name = a;
    }
    public String getName(){
        return this.name;
    }
    @Column(name="manager",nullable = false)
    private int managerId;
    public void setManagerId(int a){
        this.managerId = a;
    }
    public int getManagerId(){
        return this.managerId;
    }
}
