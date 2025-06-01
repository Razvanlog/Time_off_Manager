package com.Turtles.Time_off_Manager_BackEnd.User;

import com.Turtles.Time_off_Manager_BackEnd.web.transfer.AdminUpdateUserRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.ProjectResponse;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.TimeOffRequestResponse;

import jakarta.validation.Valid; // Added for DTO validation
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
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserResponseMapper userResponseMapper;

    @Autowired
    private UserRepository userRepository;

    // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/signup")
    @Operation(summary = "Sign up a new user")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User user = service.save(createUserRequest);
        return ResponseEntity.ok(userResponseMapper.map(user));
    }

    // @CrossOrigin(origins = "http://localhost:4200")
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

    // @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    @Operation(summary = "Get all users") // Can be used by Admin
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = service.findAll();
        return ResponseEntity.ok(users);
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/email/{email}")
    @Operation(summary = "Get a user by email")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        UserResponse user = service.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/email/{email}")
    @Operation(summary = "Delete user by email")
    @ApiResponse(responseCode = "200", description = "success")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String email) {
        UserResponse user = service.delete(email);
        if (user == null && !service.exists(email)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // @CrossOrigin(origins="http://localhost:4200") // Redundant
    @PutMapping("/email/{email}")
    @Operation(summary="Modify user by current email")
    @ApiResponse(responseCode="200",description = "succes")
    public ResponseEntity<UserResponse> modifyUserByEmail(@PathVariable String email, @Valid @RequestBody CreateUserRequest createUserRequest) { // Added @Valid
        UserResponse user = service.modify(email,createUserRequest);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }


    @GetMapping("/id/{userId}")
    @Operation(summary = "ADMIN: Get user by numerical ID")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserResponse> adminGetUserById(@PathVariable Long userId) {
        UserResponse userResponse = service.findById(userId);
        if (userResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/id/{userId}")
    @Operation(summary = "ADMIN: Update user details by numerical ID (name, email, role)")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "400", description = "Invalid request data (e.g., email taken)")
    public ResponseEntity<UserResponse> adminUpdateUser(
            @PathVariable Long userId,
            @Valid @RequestBody AdminUpdateUserRequest updateRequest) {
        try {
            UserResponse updatedUser = service.adminUpdateUser(userId, updateRequest);

            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("User not found")) {
                return ResponseEntity.notFound().build();
            }

            if (e instanceof IllegalArgumentException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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