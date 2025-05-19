package com.Turtles.Time_off_Manager_BackEnd.LeaveBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("LeaveBalanceController")
public class LeaveBalanceController {
    @Autowired
    private LeaveBalanceService service;
    @PostMapping
    @Operation(summary="Create LeaveBalance")
    @ApiResponse(responseCode="200", description="succes")
    public ResponseEntity<LeaveBalance> SaveLeaveBalance(@RequestBody LeaveBalance leaveBalance) {
        LeaveBalance a=service.save(leaveBalance);
        return ResponseEntity.ok(a);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<LeaveBalance> getLeaveBalance(@PathVariable("userID") int userId) {
        LeaveBalance leaveBalance=service.findByUserId((long) userId);
        return ResponseEntity.ok(leaveBalance);
    }
}
