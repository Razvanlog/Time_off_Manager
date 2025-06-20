package com.Turtles.Time_off_Manager_BackEnd.Projects;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserService;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateProjectRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateUserRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.ProjectResponse;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private UserService userService;
    @PostMapping
    @Operation(summary="Create Project")
    @ApiResponse(responseCode = "200", description="succes")
    public ResponseEntity<ProjectResponse> saveProject(@RequestBody CreateProjectRequest projects){
        return ResponseEntity.ok(projectsService.save(projects));
    }
    @GetMapping
    @Operation(summary="Get all projects")
    @ApiResponse(responseCode="200", description="succes")
    public ResponseEntity<List<ProjectResponse>> getAllProjects(){
        return ResponseEntity.ok(projectsService.findAll());
    }
    @GetMapping("/{managerEmail}")
    @Operation(summary="Get project by Manager email")
    @ApiResponse(responseCode="200",description="succes")
    public ResponseEntity<ProjectResponse> getProjectByManagerEmail(@PathVariable String managerEmail){
//        Projects a=projectsService.findId(id);
        User a=userService.findRawByEmail(managerEmail);
        if (a==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projectsService.findByManager(managerEmail));
    }
//    @PutMapping("/{id}")
//    @Operation(summary="Modify Project")
//    @ApiResponse(responseCode = "200", description="succes")
//    public ResponseEntity<Projects> updateProject(@PathVariable int id, @RequestBody Projects projects){
//        if (projectsService.exists(id)){
//            projectsService.delete(id);
//            projectsService.save(projects);
//            return ResponseEntity.ok(projects);
////            return ResponseEntity.ok(projectsService.save(projects));
//        }
//        return ResponseEntity.notFound().build();
//    }
    @DeleteMapping("/{Name}")
    @Operation(summary="Delete Project")
    @ApiResponse(responseCode = "200", description="succes")
    public ResponseEntity<ProjectResponse> deleteProject(@PathVariable String Name){
//        Projects a=projectsService.findId(id);
//        if (a==null){
//            return ResponseEntity.notFound().build();
//        }
//        projectsService.delete(id);
//        return ResponseEntity.ok(a);
        ProjectResponse a=projectsService.delete(Name);
        return ResponseEntity.ok(a);
    }
    @PutMapping("/{Name}")
    @Operation(summary="Modify Project")
    @ApiResponse(responseCode = "200", description = "succes")
    public ResponseEntity<ProjectResponse> modifyProject(@PathVariable String Name, CreateProjectRequest project){
        ProjectResponse a=projectsService.modify(Name,project);
        return ResponseEntity.ok(a);
    }
    @PutMapping("/{Name}/{employeeEmail}")
    @Operation(summary="Add employee to project")
    @ApiResponse(responseCode = "200", description="succes")
    public void addEmployee(@PathVariable String Name, @PathVariable String employeeEmail){
        projectsService.addEmployee(Name,employeeEmail);
        return;
    }
}
