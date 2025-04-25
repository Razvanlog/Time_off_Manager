package com.Turtles.Time_off_Manager_BackEnd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name="USERS")
public class User {
    @Id
    @Column(name="user_id")
    private int user_id;
    @Column (name="name")
    private String name;
    @Column (name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="role_id")
    private int role_id;
}
