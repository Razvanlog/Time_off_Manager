package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;

import com.Turtles.Time_off_Manager_BackEnd.User.UserResponseMapper;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.TimeOffRequestResponse;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class TimeOffRequestResponseMapper {
    UserResponseMapper userResponseMapper;
    public TimeOffRequestResponse map(TimeOffRequest timeOffRequest) {
        TimeOffRequestResponse timeOffRequestResponse = new TimeOffRequestResponse();
        timeOffRequestResponse.setDescription(timeOffRequest.getDescription());
        timeOffRequestResponse.setEnd(timeOffRequest.getEndDate());
        timeOffRequestResponse.setStart(timeOffRequest.getStartDate());
        timeOffRequestResponse.setRequestUserNumber(timeOffRequest.getRequestUserNumber());
//        UserResponse user=userResponseMapper.map(timeOffRequest.getUser());
        timeOffRequestResponse.setUser(timeOffRequest.getUser().getEmail());
        if (timeOffRequest.getLeaveType() != null) {
            timeOffRequestResponse.setLeaveType(timeOffRequest.getLeaveType().name());
        }
        timeOffRequestResponse.setStatus(timeOffRequest.getStatus());
        return timeOffRequestResponse;
    }
}
