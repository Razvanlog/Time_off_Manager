package com.Turtles.Time_off_Manager_BackEnd.LeaveType;
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
@RequestMapping("/leaveTypes")
public class LeaveTypeController {
    @Autowired
    private LeaveTypeService service;
    @PostMapping
    @Operation(summary="Create leaveType")
    @ApiResponse(responseCode = "200", description="succes")
    public ResponseEntity<LeaveType> saveLeaveType(@RequestBody LeaveType leavetype){
        LeaveType a=service.save(leavetype);
        return ResponseEntity.ok(a);
    }
    @GetMapping()
    public ResponseEntity<List<LeaveType>> GetAllLeaveTypes() {
        List<LeaveType> leaveTypeList = service.findAll();
        if (leaveTypeList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(leaveTypeList);
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            return mapper.writeValueAsString(leaveTypesList);
//        }
//        catch (JsonProcessingException e) {
//            e.clearLocation();
//        }
//        return "getAllLeaveTypes";
    }
}
