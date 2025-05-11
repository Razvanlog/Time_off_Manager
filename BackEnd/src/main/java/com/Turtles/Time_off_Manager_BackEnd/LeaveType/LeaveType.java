package com.Turtles.Time_off_Manager_BackEnd.LeaveType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.Getter;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="LEAVETYPES")
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="leaveTypeId")
    private int leaveTypeId;
    public int getLeaveTypeId() {
        return leaveTypeId;
    }
    public void setLeaveTypeId(int a) {
        leaveTypeId=a;
    }
    @Column(name="name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String a) {
        name=a;
    }
}
