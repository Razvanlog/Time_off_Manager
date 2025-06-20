package com.Turtles.Time_off_Manager_BackEnd.Projects;
import com.Turtles.Time_off_Manager_BackEnd.User.*;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateProjectRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateUserRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.ProjectResponse;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.UserResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProjectsService {
    @Autowired
    private ProjectsRepository repo;
    @Autowired
    private UserRepository userRepo;
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
        //
        if (project.getEmployees().contains(project.getManager())){
            return null;
        }
        Projects a=mapper.map(project);
        Optional<User> manager=userRepo.findByEmail(project.getManager());
        if (manager.isEmpty()){
            return null;
        }
        System.out.println(a.getId());
        a.setManager(manager.get());
        System.out.println(manager.get().getUserId());
        a.setEmployees(new ArrayList<User>());
        for (String it: project.getEmployees()){
            Optional<User> employee=userRepo.findByEmail(it);
            if (employee.isEmpty()){
                continue;
            }
            a.addEmployee(employee.get());
            System.out.println(employee.get().getUserId());
        }
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

    public List<User> getEmployees(String managerEmail){
        Optional<User> manager=userRepo.findByEmail(managerEmail);
        if (manager.isEmpty()){
            return null;
        }
        Optional<Projects> a=repo.findByManager(manager.get());
        if (a.isEmpty()){
            return null;
        }
        return a.get().getEmployees();
    }
    public ProjectResponse findByName(String name){
        Optional<Projects> p=repo.findByName(name);
        if (p.isEmpty()){
            return null;
        }
        ProjectResponse r=responseMapper.map(p.get());
        return r;
    }
    public ProjectResponse findByManager(String managerEmail){
        Optional<User> manager=userRepo.findByEmail(managerEmail);
        if (manager.isEmpty()){
            return null;
        }
        Optional<Projects> a=repo.findByManager(manager.get());
        if (a.isEmpty()){
            return null;
        }
        ProjectResponse r=responseMapper.map(a.get());
        return r;
    }
//    public ProjectResponse findByManager(User user){
//        Optional<Projects> b=repo.findByName(user.getName());
//        if (b.isEmpty()){
//            return null;
//        }
//        return responseMapper.map(b.get());
//    }
    public List<ProjectResponse> findAll(){
        return repo.findAll().stream().map(responseMapper::map).toList();
    }
    public ProjectResponse delete(String project) {
        Optional <Projects> a=repo.findByName(project);
        if (a.isEmpty()){
            return null;
        }
        repo.delete(a.get());
        return projectResponseMapper.map(a.get());
    }
    public ProjectResponse modify(String projectName, CreateProjectRequest project) {
        Optional<Projects> a=repo.findByName(projectName);
        if (a.isEmpty()){
            return null;
        }
        Projects b=a.get();
        b.setName(project.getName());
        Optional<User> manager=userRepo.findByEmail(project.getManager());
        if (manager.isEmpty()){
            return null;
        }
        b.setManager(manager.get());
        b.setEmployees(new ArrayList<>());
        for (String it: project.getEmployees()){
            Optional<User> employee=userRepo.findByEmail(it);
            if (employee.isEmpty()){
                continue;
            }
            b.addEmployee(employee.get());
        }
        repo.save(b);
        return responseMapper.map(b);
    }
    public void addEmployee(String projectName, String employeeEmail) {
        Optional<Projects> a=repo.findByName(projectName);
        if (a.isEmpty()){
            return;
        }
        Optional<User> employee=userRepo.findByEmail(employeeEmail);
        if (employee.isEmpty()){
            return;
        }
        Projects b=a.get();
        b.addEmployee(employee.get());
        repo.save(b);
    }
}
