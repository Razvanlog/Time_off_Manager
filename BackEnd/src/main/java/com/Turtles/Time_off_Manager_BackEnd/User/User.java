package com.Turtles.Time_off_Manager_BackEnd.User;

import com.Turtles.Time_off_Manager_BackEnd.Role.Role;
import com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest.TimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.Projects.Projects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
//import lombok.Getter;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long userId;
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long a) {
        userId=a;
    }
    @Column (name="name",unique = true, nullable = false)
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String a) {
        name=a;
    }
    @Column (name="email", unique = true, nullable = false)
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String a) {
        email=a;
    }
    @Column(name="password", nullable = false)
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String a) {
        password=a;
    }
//    @OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)
//    @JoinColumn(name="requestsid",nullable = true)
//    private List<TimeOffRequest> requests;
//    public List<TimeOffRequest> getTimeOffRequests() {return requests;}
//    public void setTimeOffRequests(List<TimeOffRequest> a) {
//        requests=a;
//    }
//    public void addTimeOffRequest(TimeOffRequest a) {
//        requests.add(a);
//    }
//    public void removeTimeOffRequest(TimeOffRequest a) {
//        requests.remove(a);
//    }
//    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name="projectid",nullable = true)
//    private Projects project;
//    public Projects getProject() {
//        return project;
//    }
//    public void setProject(Projects a) {
//        project=a;
//    }
    @Enumerated(EnumType.STRING)
    @Column(name="role",nullable = false)
    private Role role;
    public Role getRole(){return role;}
    public void setRole(Role role){this.role=role;}


    // Add this inside your User class
    @OneToMany(mappedBy = "manager")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private java.util.List<Projects> managedProjects;
//    @Column(name="role")
//    private int roleId;
//    public int getRoleId() {
//        return roleId;
//    }
//    public void setRoleId(int a) {
//        roleId=a;
//    }
}
