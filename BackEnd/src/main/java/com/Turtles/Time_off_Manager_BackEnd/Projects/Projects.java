package com.Turtles.Time_off_Manager_BackEnd.Projects;

import com.Turtles.Time_off_Manager_BackEnd.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    // THIS IS THE ONLY CHANGE
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="managerid", nullable = false)
    private User manager;

    @OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn(name="employees")
    private List<User> employees;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String a){
        this.name = a;
    }
    public String getName(){
        return this.name;
    }
    public void setManager(User a){
        this.manager = a;
    }
    public User getManager(){
        return this.manager;
    }
    public void setEmployees(List<User> a){
        this.employees = a;
    }
    public void addEmployee(User a){
        this.employees.add(a);
    }
    public void removeEmployee(User a){
        this.employees.remove(a);
    }
    public List<User> getEmployees(){
        return this.employees;
    }
}