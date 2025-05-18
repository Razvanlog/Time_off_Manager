package com.Turtles.Time_off_Manager_BackEnd.Projects;


import com.Turtles.Time_off_Manager_BackEnd.User.UserResponseMapper;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.ProjectResponse;
import org.springframework.stereotype.Component;

@Component
public class ProjectResponseMapper {
    private final UserResponseMapper userResponseMapper=new UserResponseMapper();
    public ProjectResponse map(Projects project){
        ProjectResponse projectResponse=new ProjectResponse();
        projectResponse.setName(project.getName());
        projectResponse.setManager(userResponseMapper.map(project.getManager()));
        project.getEmployees().stream().map(userResponseMapper::map).forEach(projectResponse.getEmployees()::add);
        return projectResponse;
    }
}
