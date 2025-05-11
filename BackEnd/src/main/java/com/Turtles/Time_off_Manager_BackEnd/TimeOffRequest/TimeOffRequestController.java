package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<TimeOffRequest> saveTimeOffRequest(@RequestBody TimeOffRequest timeOffRequests) {
        TimeOffRequest a=service.save(timeOffRequests);
        return ResponseEntity.ok(a);
    }
    @GetMapping("{user}")
    @Operation(summary="get TimeOffRequests with UserID")
    public ResponseEntity<List<TimeOffRequest>> getTimeOffRequests(@PathVariable("user") User user) {
        List<TimeOffRequest> a=service.findByUserId(user);
        return ResponseEntity.ok(a);
    }
}
