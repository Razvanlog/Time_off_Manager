package com.Turtles.Time_off_Manager_BackEnd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.Getter;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int user_id;
    @Column (name="name")
    private String name;
    public String GetName() {
        return name;
    }
    public void setName(String a) {
        name=a;
    }
    @Column (name="email")
    private String email;
    public String GetEmail() {
        return email;
    }
    public void setEmail(String a) {
        email=a;
    }
    @Column(name="password")
    private String password;
    public String GetPassword() {
        return password;
    }
    public void setPassword(String a) {
        password=a;
    }
    @Column(name="role")
    private int role_id;
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int a) {
        user_id=a;
    }
}
