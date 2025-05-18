package com.Turtles.Time_off_Manager_BackEnd.User;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {
    public UserResponse map(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        return userResponse;
    }
}
