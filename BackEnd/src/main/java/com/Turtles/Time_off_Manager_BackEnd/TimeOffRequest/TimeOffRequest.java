package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
//import lombok.Getter;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TimeOffRequests")
public class TimeOffRequest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id",nullable = false,unique = true)
    private int id;
    public int getRequestsId() {
        return id;
    }
    public void setRequestsId(int a) {
        this.id= a;
    }
    @Column(name="user",nullable = false)
    private int user;
    public int getUser() {
        return user;
    }
    public void setUser(int user) {
        this.user = user;
    }
    @Column(name="start")
    LocalDate start;
    public LocalDate getStart() {
        return start;
    }
    public void setStart(LocalDate start) {
        this.start = start;
    }
    @Column(name="end")
    LocalDate end;
    public LocalDate getEnd() {
        return end;
    }
    public void setEnd(LocalDate end) {
        this.end = end;
    }
    @Column(name="status")
    int status;
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
