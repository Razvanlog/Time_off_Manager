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
    @Column(name="requestsId")
    private int requestsId;
    public int getRequestsId() {
        return requestsId;
    }
    public void setRequestsId(int a) {
        this.requestsId= a;
    }
    @ManyToOne
    @JoinColumn(name="USERS",referencedColumnName="userid")
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
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
