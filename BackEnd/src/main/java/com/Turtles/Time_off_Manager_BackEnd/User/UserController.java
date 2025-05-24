package com.Turtles.Time_off_Manager_BackEnd.User;

import com.Turtles.Time_off_Manager_BackEnd.web.transfer.ProjectResponse;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.TimeOffRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateUserRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.UserResponse;

@RestController

// changed path to match ang exp
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserResponseMapper userResponseMapper;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/signup")
    @Operation(summary = "Sign up a new user")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<UserResponse> signup(@RequestBody CreateUserRequest createUserRequest) {
        User user = service.save(createUserRequest);
        return ResponseEntity.ok(userResponseMapper.map(user));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    @Operation(summary = "Login with email and password")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<UserResponse> login(@RequestBody CreateUserRequest request) {
        User user = service.findRawByEmail(request.getEmail());
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userResponseMapper.map(user));
    }
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping
//    @Operation(summary = "Create User")
//    @ApiResponse(responseCode = "200", description = "success")
//    public ResponseEntity<UserResponse> saveUser(@RequestBody CreateUserRequest createUserRequest) {
//        User a = service.save(createUserRequest);
//        return ResponseEntity.ok(userResponseMapper.map(a));
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = service.findAll();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{email}")
    @Operation(summary = "Get a user by email")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        UserResponse user = service.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{email}")
    @Operation(summary = "Delete user by email")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String email) {
        UserResponse user = service.delete(email);
        return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PutMapping("/{email}")
    @Operation(summary="Modify user")
    @ApiResponse(responseCode="200",description = "succes")
    public ResponseEntity<UserResponse> modifyUser(@PathVariable String email, @RequestBody CreateUserRequest createUserRequest) {
        UserResponse user = service.modify(email,createUserRequest);
        return ResponseEntity.ok(user);
    }


//    @CrossOrigin(origins="http://localhost:4200")
//    @GetMapping("/{email}/requests")
//    @Operation(summary="Get all requests of user")
//    @ApiResponse(responseCode="200", description="succes")
//    public ResponseEntity<List<TimeOffRequestResponse>> getAllRequests(@PathVariable String email) {
//        return ResponseEntity.ok(service.getRequests(email));
//    }

//    @CrossOrigin(origins="http://localhost:4200")
//    @GetMapping("/{email}/project")
//    @Operation(summary="Get project of user")
//    @ApiResponse(responseCode="200", description="succes")
//    public ResponseEntity<ProjectResponse> getProject(@PathVariable String email) {
//        return ResponseEntity.ok(service.getProjects(email));
//    }
    /*
    // unfinished !!!!!!!!!!!!
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    @Operation(summary = "Modify User")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        if (service.exists(id)) {
            User u = service.findById(id);
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            return ResponseEntity.ok(service.save(u));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    */
}
