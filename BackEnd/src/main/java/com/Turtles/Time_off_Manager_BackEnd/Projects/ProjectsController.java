package com.Turtles.Time_off_Manager_BackEnd.Projects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
@RestController
@RequestMapping("/project")
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;
    @PostMapping
    @Operation(summary="Create Project")
    @ApiResponse(responseCode = "200", description="succes")
    public ResponseEntity<Projects> saveProject(@RequestBody Projects projects){
        return ResponseEntity.ok(projectsService.save(projects));
    }
    @GetMapping
    @Operation(summary="Get all projects")
    @ApiResponse(responseCode="200", description="succes")
    public ResponseEntity<List<Projects>> getAllProjects(){
        return ResponseEntity.ok(projectsService.findAll());
    }
    @GetMapping("/{id}")
    @Operation(summary="Get project")
    @ApiResponse(responseCode="200",description="succes")
    public ResponseEntity<Projects> getProjectById(@PathVariable int id){
        Projects a=projectsService.findId(id);
        if (a==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(a);
    }
    @PutMapping("/{id}")
    @Operation(summary="Modify Project")
    @ApiResponse(responseCode = "200", description="succes")
    public ResponseEntity<Projects> updateProject(@PathVariable int id, @RequestBody Projects projects){
        if (projectsService.exists(id)){
            projectsService.delete(id);
            projectsService.save(projects);
            return ResponseEntity.ok(projects);
//            return ResponseEntity.ok(projectsService.save(projects));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @Operation(summary="Delete Project")
    @ApiResponse(responseCode = "200", description="succes")
    public ResponseEntity<Projects> deleteProject(@PathVariable int id){
        Projects a=projectsService.findId(id);
        if (a==null){
            return ResponseEntity.notFound().build();
        }
        projectsService.delete(id);
        return ResponseEntity.ok(a);
    }
}
