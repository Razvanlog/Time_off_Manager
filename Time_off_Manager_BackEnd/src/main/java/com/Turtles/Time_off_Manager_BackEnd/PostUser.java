package com.Turtles.Time_off_Manager_BackEnd;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/PostUser")
public class PostUser {
    @Autowired
    private UserService service;
    @GetMapping("/")
    public String ShowHomePage(){
        return "HomePage";
    }
    @PostMapping("Post")
    @Operation(summary="Create User")
    @ApiResponse(responseCode="200", description = "succes")
    public String SaveUser(@RequestBody User user,Model model){
        int id=service.save(user).getUser_id();
        String name=service.save(user).GetName();
        String mess="User with id: "+id+","+name+" has been saved";
        model.addAttribute("mess",mess);
        return "createUserPage "+mess;
    }
    @GetMapping("/getAllUsers")
    public String GetAllUsers(@RequestParam(value="message",required=false) String message, Model model){
        List<User> users=service.findAll();
        model.addAttribute("list",users);
        model.addAttribute("message",message);
        return "getAllUsers";
    }
}
