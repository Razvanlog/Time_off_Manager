package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;

import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.LeaveType.LeaveType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TIME_OFF_REQUESTS")
public class TimeOffRequest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="request_id")
    private Long id;

    @Column(name="description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="leave_type", nullable = false)
    private LeaveType leaveType;

    @Column(name="status")
    private int status;

    @Column(name="start_date", nullable = false)
    private LocalDate startDate;

    @Column(name="end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name="user_email",nullable=false)
    private User user;

    @Column(name="requested_days", nullable = false)
    private Integer requestedDays;

    @Column(name="requestUserNumber",nullable = false)
    private Long requestUserNumber;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public LeaveType getLeaveType(){
        return this.leaveType;
    }
    public void setLeaveType(LeaveType leaveType){
        this.leaveType = leaveType;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRequestedDays() {
        return requestedDays;
    }
    public void setRequestedDays(Integer requestedDays) {
        this.requestedDays = requestedDays;
    }

    public Long getRequestUserNumber() {return requestUserNumber;}
    public void setRequestUserNumber(Long requestUserNumber) {this.requestUserNumber = requestUserNumber;}


}