package com.Turtles.Time_off_Manager_BackEnd.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.Getter;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid", unique = true)
    private int userId;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int a) {
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
    @Column(name="role")
    private int roleId;
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int a) {
        roleId=a;
    }
}
