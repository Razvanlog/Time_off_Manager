package com.Turtles.Time_off_Manager_BackEnd.Assignments;
import com.Turtles.Time_off_Manager_BackEnd.Projects.ProjectsService;
import com.Turtles.Time_off_Manager_BackEnd.User.UserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserService;
import com.Turtles.Time_off_Manager_BackEnd.Projects.Projects;
import com.Turtles.Time_off_Manager_BackEnd.Projects.ProjectsService;

import java.util.List;
import java.util.Optional;
@Service
public class AssignmentsService {
    @Autowired
    private AssignmentsRepo repo;
    @Autowired
    UserService userService;
    @Autowired
    ProjectsService projectService;
    public Assignments save(Assignments assignments) {
        User a=userService.findById(assignments.getUser());
        Projects b=projectService.findId(assignments.getProject());
        if (a!=null && b!=null) {
            return repo.save(assignments);
        }
        else return null;
    }
    public List<Assignments> findByUser(int id){
        List<Assignments> a=repo.findByUser(id);
        return a;
    }
    public List<Assignments> findByProject(int project){
        List<Assignments> a=repo.findByProject(project);
        return a;
    }
    public Assignments delete(int id) {
        if (repo.existsById(id)) {
            Assignments a=repo.findById(id).get();
            repo.deleteById(id);
            return a;
        }
        return null;
    }
    public boolean exists(int id){
        return repo.existsById(id);
    }
}
