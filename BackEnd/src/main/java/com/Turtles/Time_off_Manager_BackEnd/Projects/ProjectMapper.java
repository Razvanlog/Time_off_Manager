package com.Turtles.Time_off_Manager_BackEnd.Projects;

import com.Turtles.Time_off_Manager_BackEnd.User.UserMapper;
import com.Turtles.Time_off_Manager_BackEnd.User.UserService;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    private final UserMapper userMapper= new UserMapper();
    public Projects map(CreateProjectRequest createProjectRequest){
        Projects a=new Projects();
        a.setName(createProjectRequest.getName());
//        a.setManager(createProjectRequest.getManager());
//        createProjectRequest.getEmployees().forEach(a.getEmployees()::add);
        return a;
    }
}
