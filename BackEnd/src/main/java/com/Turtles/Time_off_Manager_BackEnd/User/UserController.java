package com.Turtles.Time_off_Manager_BackEnd.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping
    @Operation(summary="Create User")
    @ApiResponse(responseCode="200", description = "succes")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User a=service.save(user);
        return ResponseEntity.ok(a);
    }
    @GetMapping
    @Operation(summary="Get all users")
    @ApiResponse(responseCode="200", description="succes")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=service.findAll();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    @Operation(summary="get a User with id")
    @ApiResponse(responseCode="200", description="succes")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user=service.findById(id);
        if (user==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @DeleteMapping
    @Operation(summary="Delete User by Id")
    @ApiResponse(responseCode="200", description="succes")
    public ResponseEntity<User> deleteUserByID(@RequestBody int id){
        User user=service.findById(id);
        service.delete(id);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    @Operation(summary="Modify User")
    @ApiResponse(responseCode="200", description="succes")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        if (service.exists(id)){
            User u=service.findById(id);
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            return ResponseEntity.ok(service.save(u));
        }
        else return ResponseEntity.notFound().build();
    }
}
