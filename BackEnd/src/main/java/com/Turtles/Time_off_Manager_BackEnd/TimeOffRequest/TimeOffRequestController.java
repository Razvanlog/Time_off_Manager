package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
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
    @PostMapping
    @Operation(summary="Create TimeOffRequests")
    @ApiResponse(responseCode="200",description="succes")
    public ResponseEntity<TimeOffRequestResponse> saveTimeOffRequest(@RequestBody CreateTimeOffRequest timeOffRequests) {
        TimeOffRequestResponse a=service.save(timeOffRequests);
        return ResponseEntity.ok(a);
    }
    @GetMapping("{user}")
    @Operation(summary="get TimeOffRequests with UserID")
    public ResponseEntity<List<TimeOffRequestResponse>> getTimeOffRequests(@PathVariable("user") CreateUserRequest user) {
        List<TimeOffRequestResponse> a=service.findByUser(user.getEmail());
        return ResponseEntity.ok(a);
    }
}
