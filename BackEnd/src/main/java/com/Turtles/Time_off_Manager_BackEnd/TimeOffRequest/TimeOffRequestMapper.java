package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;

import com.Turtles.Time_off_Manager_BackEnd.LeaveType.LeaveType;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserMapper;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateTimeOffRequest;
import org.springframework.stereotype.Component;

@Component
public class TimeOffRequestMapper {

    public TimeOffRequest map(CreateTimeOffRequest createTimeOffRequest, User userEntity) {
        TimeOffRequest timeOffRequest = new TimeOffRequest();

        timeOffRequest.setUser(userEntity);

        timeOffRequest.setStartDate(createTimeOffRequest.getStart());
        timeOffRequest.setEndDate(createTimeOffRequest.getEnd());
        timeOffRequest.setDescription(createTimeOffRequest.getDescription());

        if (createTimeOffRequest.getLeaveType() != null && !createTimeOffRequest.getLeaveType().trim().isEmpty()) {
            try {
                LeaveType leaveTypeEnum = LeaveType.fromString(createTimeOffRequest.getLeaveType());
                timeOffRequest.setLeaveType(leaveTypeEnum);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid leave type: " + createTimeOffRequest.getLeaveType(), e);
            }
        } else {
            throw new IllegalArgumentException("Leave type is required.");
        }

        if (createTimeOffRequest.getRequestedDays() != null && createTimeOffRequest.getRequestedDays() > 0) {
            timeOffRequest.setRequestedDays(createTimeOffRequest.getRequestedDays());
        } else {
            throw new IllegalArgumentException("Requested days are required and must be positive.");
        }

        return timeOffRequest;
    }


}