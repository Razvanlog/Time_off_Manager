package com.Turtles.Time_off_Manager_BackEnd.Projects;


import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserResponseMapper;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.ProjectResponse;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.UserResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProjectResponseMapper {
    private final UserResponseMapper userResponseMapper=new UserResponseMapper();
    public ProjectResponse map(Projects project){
        ProjectResponse projectResponse=new ProjectResponse();
        projectResponse.setName(project.getName());
        projectResponse.setManager(userResponseMapper.map(project.getManager()));
        projectResponse.setEmployees(new ArrayList<UserResponse>());
        if (project.getEmployees() != null && !project.getEmployees().isEmpty())
            for (User a:project.getEmployees()){
                projectResponse.getEmployees().add(userResponseMapper.map(a));
            }
//        if (project.getManager()!=null)
//        project.getEmployees().stream().map(userResponseMapper::map).forEach(projectResponse.getEmployees()::add);
        return projectResponse;
    }
}
