package com.Turtles.Time_off_Manager_BackEnd.Projects;

import com.Turtles.Time_off_Manager_BackEnd.User.UserMapper;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateProjectRequest;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    private final UserMapper userMapper= new UserMapper();
    public Projects map(CreateProjectRequest createProjectRequest){
        Projects a=new Projects();
        a.setName(createProjectRequest.getName());
        a.setManager(userMapper.map(createProjectRequest.getManager()));
        createProjectRequest.getEmployees().stream().map(userMapper::map).forEach(a.getEmployees()::add);
        return a;
    }
}
