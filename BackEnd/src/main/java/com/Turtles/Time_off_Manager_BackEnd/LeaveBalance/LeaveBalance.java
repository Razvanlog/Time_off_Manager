package com.Turtles.Time_off_Manager_BackEnd.LeaveBalance;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
//import lombok.Getter;
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveID;
    public int getLeaveID() {
        return leaveID;
    }
    public void setLeaveID(int leaveID) {
        this.leaveID = leaveID;
    }
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="userId")
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) { this.user = user; }
    private int balance;
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
