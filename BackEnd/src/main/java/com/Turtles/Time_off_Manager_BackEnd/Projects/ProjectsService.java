package com.Turtles.Time_off_Manager_BackEnd.Projects;
import com.Turtles.Time_off_Manager_BackEnd.User.UserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import java.util.List;
import java.util.Optional;
@Service
public class ProjectsService {
    @Autowired
    private ProjectsRepository repo;
    @Autowired
    private UserService userService;
    public Projects save(Projects project) {
        User a=userService.findById(project.getManagerId());
        if (a!=null)
            return repo.save(project);
        else return null;
    }
    public Projects findId(int id){
        if (repo.existsById(id)){
            return repo.findById(id).get();
        }
        return null;
//        return repo.findById(id).get();
    }
    public User findManager(int managerId){
        return userService.findById(managerId);
    }
    public List<Projects> findAll(){
        return repo.findAll();
    }
    public boolean exists(int id){
        return repo.existsById(id);
    }
    public Projects delete(int id){
        if (repo.existsById(id)) {
            Projects projects = repo.findById(id).get();
            repo.deleteById(id);
            return projects;
        }
        return null;
    }
}
