package com.Turtles.Time_off_Manager_BackEnd.User;

import org.springframework.stereotype.Component;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateUserRequest;
@Component
public class UserMapper {
    public User map(CreateUserRequest createUserRequest) {
        User user=new User();
        user.setName(createUserRequest.getName());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        return user;
    }
}
