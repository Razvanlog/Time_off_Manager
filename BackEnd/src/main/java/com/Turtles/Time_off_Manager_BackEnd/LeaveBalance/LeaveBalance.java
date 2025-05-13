package com.Turtles.Time_off_Manager_BackEnd.LeaveBalance;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
//import lombok.Getter;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="LeaveBalance")
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
//    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @Column(name="user")
    private int user;
    public int getUser() {
        return user;
    }
    public void setUser(int user) { this.user = user; }
    private int balance;
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
