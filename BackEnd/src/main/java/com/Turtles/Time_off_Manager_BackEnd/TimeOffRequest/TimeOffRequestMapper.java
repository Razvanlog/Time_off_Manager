package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;

import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserMapper;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateTimeOffRequest;
import org.springframework.stereotype.Component;

@Component
public class TimeOffRequestMapper {
    private final UserMapper userMapper=new UserMapper();
    public TimeOffRequest map(CreateTimeOffRequest createTimeOffRequest) {
        TimeOffRequest timeOffRequest = new TimeOffRequest();
        User user =userMapper.map(createTimeOffRequest.getUser());
        timeOffRequest.setUser(user);
        timeOffRequest.setStart(createTimeOffRequest.getStart());
        timeOffRequest.setEnd(createTimeOffRequest.getEnd());
        timeOffRequest.setDescription(createTimeOffRequest.getDescription());
        timeOffRequest.setStatus(createTimeOffRequest.getStatus());
        timeOffRequest.setType(createTimeOffRequest.getType());
        return timeOffRequest;
    }
}
