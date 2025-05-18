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
    @Column(name="description")
    private String description;
    @Column(name="type")
    private int type;
    @Column(name="status")
    private int status;
    @Column(name="start")
    private LocalDate start;
    @Column(name="end")
    private LocalDate end;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    public String getDescription(){return this.description;}
    public void setDescription(String description){this.description = description;}
    public int getType(){return this.type;}
    public void setType(int type){this.type = type;}
    public int getRequestsId() {
        return id;
    }
    public void setRequestsId(int a) {
        this.id= a;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LocalDate getStart() {
        return start;
    }
    public void setStart(LocalDate start) {
        this.start = start;
    }
    public LocalDate getEnd() {
        return end;
    }
    public void setEnd(LocalDate end) {
        this.end = end;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
