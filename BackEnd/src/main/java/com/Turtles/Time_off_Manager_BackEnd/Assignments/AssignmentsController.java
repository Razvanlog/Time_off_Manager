package com.Turtles.Time_off_Manager_BackEnd.Assignments;
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
@RequestMapping("/assignment")
public class AssignmentsController {
    @Autowired
    AssignmentsService assignmentsService;
    @PostMapping
    @Operation(summary="Create Assignment")
    @ApiResponse(responseCode="200",description="succes")
    public ResponseEntity<Assignments> saveAssignment(@RequestBody Assignments assignments) {
        return ResponseEntity.ok(assignmentsService.save(assignments));
    }
    @GetMapping
    @Operation(summary="Get all projects")
    @ApiResponse(responseCode="200",description="succes")
    public ResponseEntity<List<Assignments>> getAllAssignments() {
        return ResponseEntity.ok(assignmentsService.findAll());
    }
    @GetMapping("/{id}")
    @Operation(summary="Get all projects of a user")
    @ApiResponse(responseCode="200", description="succes")
    public ResponseEntity<List<Assignments>> getAllAssignmentsByUser(@PathVariable int id) {
        List<Assignments> assignments = assignmentsService.findByUser(id);
        if (assignments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(assignments);
    }
    @PutMapping("/{id}")
    @Operation(summary="Modify assignment")
    @ApiResponse(responseCode="200",description="succes")
    public ResponseEntity<Assignments> updateAssignment(@PathVariable int id,@RequestBody Assignments assignments) {
        if (assignmentsService.exists(id)) {
            assignmentsService.delete(id);
            assignmentsService.save(assignments);
            return ResponseEntity.ok(assignments);
        }
        else return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    @Operation(summary="Delete assignment")
    @ApiResponse(responseCode="200",description="succes")
    public ResponseEntity<Assignments> deleteAssignment(@PathVariable int id) {
        if (assignmentsService.exists(id)) {
            Assignments a=assignmentsService.findById(id);
            assignmentsService.delete(id);
            return ResponseEntity.ok(a);
        }
        else return ResponseEntity.noContent().build();
    }
}
