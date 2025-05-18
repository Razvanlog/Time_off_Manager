package com.Turtles.Time_off_Manager_BackEnd.Projects;
import com.Turtles.Time_off_Manager_BackEnd.User.UserMapper;
import com.Turtles.Time_off_Manager_BackEnd.User.UserResponseMapper;
import com.Turtles.Time_off_Manager_BackEnd.User.UserService;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateProjectRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateUserRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.ProjectResponse;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.UserResponse;

import java.util.List;
import java.util.Optional;
@Service
public class ProjectsService {
    @Autowired
    private ProjectsRepository repo;
    @Autowired
    private UserService userService;
    private final ProjectMapper mapper = new ProjectMapper();
    private final ProjectResponseMapper responseMapper = new ProjectResponseMapper();
    private final UserMapper userMapper=new UserMapper();
    private final UserResponseMapper userResponseMapper=new UserResponseMapper();
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectResponseMapper projectResponseMapper;

    public ProjectResponse save(CreateProjectRequest project) {
        Projects a=mapper.map(project);
        repo.save(a);
        ProjectResponse r=responseMapper.map(a);
        return r;
    }
//    public ProjectResponse findByEmail(String email){
//        UserResponse user=userService.findByEmail(email);
//        Optional<Projects> p=repo.findByManager();
//        if (p.isEmpty()){
//            return null;
//        }
//        ProjectResponse r=responseMapper.map(p.get());
//        return r;
//    }
    public ProjectResponse findByName(String name){
        Optional<Projects> p=repo.findByName(name);
        if (p.isEmpty()){
            return null;
        }
        ProjectResponse r=responseMapper.map(p.get());
        return r;
    }
    public ProjectResponse findByManager(CreateUserRequest user){
        User a=userMapper.map(user);
        Optional<Projects> b=repo.findByName(a.getName());
        if (b.isEmpty()){
            return null;
        }
        return responseMapper.map(b.get());
    }
    public List<ProjectResponse> findAll(){
        return repo.findAll().stream().map(responseMapper::map).toList();
    }
    public ProjectResponse delete(String project) {
        Optional <Projects> a=repo.findByName(project);
        if (a.isEmpty()){
            return null;
        }
        return projectResponseMapper.map(a.get());
    }
}
