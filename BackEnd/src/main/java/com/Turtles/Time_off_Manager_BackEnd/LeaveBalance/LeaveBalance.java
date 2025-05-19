package com.Turtles.Time_off_Manager_BackEnd.LeaveBalance;

import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.LeaveType.LeaveType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="LEAVE_BALANCES", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "leave_type"})
})
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


    @Enumerated(EnumType.STRING)
    @Column(name="leave_type", nullable = false)
    private LeaveType leaveType;

    public LeaveType getLeaveType() {
        return leaveType;
    }
    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    private int balance;
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}