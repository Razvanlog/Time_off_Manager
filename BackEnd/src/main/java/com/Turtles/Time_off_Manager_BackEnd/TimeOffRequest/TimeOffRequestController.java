package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;

import com.Turtles.Time_off_Manager_BackEnd.Projects.ProjectsService;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateTimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.TimeOffRequestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/TimeOffRequestsController")
@CrossOrigin(origins = "http://localhost:4200")
public class TimeOffRequestController {
    @Autowired
    private TimeOffRequestsService service;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectsService projectService;
    @PostMapping
    @Operation(summary="Create TimeOffRequests")
    @ApiResponse(responseCode="200",description="succes")
    public ResponseEntity<TimeOffRequestResponse> saveTimeOffRequest(@RequestBody CreateTimeOffRequest timeOffRequests) {
        TimeOffRequestResponse a=service.save(timeOffRequests);
        return ResponseEntity.ok(a);
    }

    @GetMapping("/{email}")
    @Operation(summary="get TimeOffRequests with Email")
    public ResponseEntity<List<TimeOffRequestResponse>> getTimeOffRequests(@PathVariable("email") String email) {
        User user=userService.findRawByEmail(email);
        if (user==null){
            return ResponseEntity.notFound().build();
        }
        List<TimeOffRequestResponse> a=service.findByUser(user.getEmail());
        return ResponseEntity.ok(a);
    }

    @GetMapping("/{userMail}/{requestNumber}")
    @Operation(summary="get TimeOffRequest with email and nr")
    @ApiResponse(responseCode = "200", description = "succes")
    public ResponseEntity<TimeOffRequestResponse> getTimeOffRequest(@PathVariable("userMail") String userMail, @PathVariable("requestNumber") int requestNumber) {
        User user=userService.findRawByEmail(userMail);
        if (user==null){
            return ResponseEntity.notFound().build();
        }
        List<TimeOffRequestResponse> a=service.findByUser(user.getEmail());
        if (a.size()<requestNumber){
            return ResponseEntity.notFound().build();
        }
        TimeOffRequestResponse a1=service.findByUserAndNr(userMail,requestNumber);
        if (a1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(a1);
    }

    @DeleteMapping("/{userMail}/{requestNumber}")
    @Operation(summary="delete TimeOffRequest with email and nr")
    @ApiResponse(responseCode ="200",description="succes")
    public ResponseEntity<TimeOffRequestResponse> deleteTimeOffRequest(@PathVariable("userMail") String userMail, @PathVariable("requestNumber") int requestNumber) {
        User user=userService.findRawByEmail(userMail);
        if (user==null){
            return ResponseEntity.notFound().build();
        }
        List<TimeOffRequestResponse> a=service.findByUser(user.getEmail());
        if (a.size()<requestNumber){
            return ResponseEntity.notFound().build();
        }
        TimeOffRequestResponse a1=service.delete(userMail,requestNumber);
        if (a1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(a1);
    }

    @PutMapping("/{userMail}/{requestNumber}")
    @Operation(summary="update TimeOffRequest with email and nr")
    @ApiResponse(responseCode ="200",description="succes")
    public ResponseEntity<TimeOffRequestResponse> modifyTimeOffRequest(
            @PathVariable("userMail") String userMail,
            @PathVariable("requestNumber") int requestNumber,
            @RequestBody CreateTimeOffRequest timeOffRequest) {
        User user=userService.findRawByEmail(userMail);
        if (user==null){
            return ResponseEntity.notFound().build();
        }
        List<TimeOffRequestResponse> a=service.findByUser(user.getEmail());
        if (a.size()<requestNumber){
            return ResponseEntity.notFound().build();
        }
        TimeOffRequestResponse a1=service.update(userMail,requestNumber,timeOffRequest);
        if (a1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(a1);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/accept/{userMail}/{requestNumber}")
    @Operation(summary="accept TimeOffRequest with email and nr")
    @ApiResponse(responseCode="200", description="succes")
    public ResponseEntity<TimeOffRequestResponse> acceptTimeOffRequest(
        @PathVariable("userMail") String userMail,
        @PathVariable("requestNumber") int requestNumber
    ){
        User user=userService.findRawByEmail(userMail);
        if (user==null){
            return ResponseEntity.notFound().build();
        }
        List<TimeOffRequestResponse> a=service.findByUser(user.getEmail());
        if (a.size()<requestNumber){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.acceptRequest(userMail,requestNumber));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/reject/{userMail}/{requestNumber}")
    @Operation(summary="reject TimeOffRequest with email and nr")
    @ApiResponse(responseCode="200", description="'succes")
    public ResponseEntity<TimeOffRequestResponse> rejectTimeOffRequest(
            @PathVariable("userMail") String userMail,
            @PathVariable("requestNumber") int requestNumber
    ){
        User user=userService.findRawByEmail(userMail);
        if (user==null){
            return ResponseEntity.notFound().build();
        }
        List<TimeOffRequestResponse> a=service.findByUser(user.getEmail());
        if (a.size()<requestNumber){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.rejectRequest(userMail,requestNumber));
    }
    @GetMapping("/all/{managerMail}")
    @Operation(summary="get requests of all employees")
    @ApiResponse(responseCode="200",description="succes")
    public ResponseEntity<List<TimeOffRequestResponse>> getTimeOffRequestsOfManager(@PathVariable("managerMail") String managerMail){
        User manager=userService.findRawByEmail(managerMail);
        if (manager==null){
            return ResponseEntity.notFound().build();
        }
        List<User> employees= projectService.getEmployees(managerMail);
        if (employees==null){
            return null;
        }
        ArrayList<TimeOffRequestResponse> requests=new ArrayList<TimeOffRequestResponse>();
        for (User employee:employees){
            requests.addAll(service.findByUser(employee.getEmail()));
        }
        return ResponseEntity.ok(requests);
    }

}
//    @PutMapping("{userMail}/{requestNumber}")
//    @Operation(summary="Modify a request")
//    public ResponseEntity<TimeOffRequestResponse> modifyTimeOffRequest(@PathVariable String userMail, @PathVariable Long requestNumber,@RequestBody TimeOffRequestResponse timeOffRequests) {
//
//    }
