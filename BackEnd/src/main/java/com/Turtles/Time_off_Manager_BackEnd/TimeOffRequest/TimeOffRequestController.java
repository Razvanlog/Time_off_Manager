package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserService;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateTimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.TimeOffRequestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/TimeOffRequestsController")
public class TimeOffRequestController {
    @Autowired
    private TimeOffRequestsService service;
    @Autowired
    private UserService userService;
    @PostMapping
    @Operation(summary="Create TimeOffRequests")
    @ApiResponse(responseCode="200",description="succes")
    public ResponseEntity<TimeOffRequestResponse> saveTimeOffRequest(@RequestBody CreateTimeOffRequest timeOffRequests) {
        TimeOffRequestResponse a=service.save(timeOffRequests);
        return ResponseEntity.ok(a);
    }
    @GetMapping("{email}")
    @Operation(summary="get TimeOffRequests with Email")
    public ResponseEntity<List<TimeOffRequestResponse>> getTimeOffRequests(@PathVariable("email") String email) {
        User user=userService.findRawByEmail(email);
        if (user==null){
            return ResponseEntity.notFound().build();
        }
        List<TimeOffRequestResponse> a=service.findByUser(user.getEmail());
        return ResponseEntity.ok(a);
    }
    @GetMapping("{userMail}/{requestNumber}")
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
        return ResponseEntity.ok(a1);
    }
//    @PutMapping("{userMail}/{requestNumber}")
//    @Operation(summary="Modify a request")
//    public ResponseEntity<TimeOffRequestResponse> modifyTimeOffRequest(@PathVariable String userMail, @PathVariable Long requestNumber,@RequestBody TimeOffRequestResponse timeOffRequests) {
//
//    }
}
