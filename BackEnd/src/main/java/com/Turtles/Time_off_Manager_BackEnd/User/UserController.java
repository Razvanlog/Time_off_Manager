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
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateUserRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.UserResponse;
@RestController

@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserResponseMapper userResponseMapper;
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping
    @Operation(summary="Create User")
    @ApiResponse(responseCode="200", description = "succes")
    public ResponseEntity<UserResponse> saveUser(@RequestBody CreateUserRequest createUserRequest){
        User a=service.save(createUserRequest);
        UserResponseMapper userResponseMapper=new UserResponseMapper();
        return ResponseEntity.ok(userResponseMapper.map(a));
    }
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping
    @Operation(summary="Get all users")
    @ApiResponse(responseCode="200", description="succes")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> users=service.findAll();
        return ResponseEntity.ok(users);
    }
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/{email}")
    @Operation(summary="get a User with email")
    @ApiResponse(responseCode="200", description="succes")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email){
        UserResponse user=service.findByEmail(email);
        if (user==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @CrossOrigin(origins="http://localhost:4200")
    @DeleteMapping("/{email}")
    @Operation(summary="Delete User by email")
    @ApiResponse(responseCode="200", description="succes")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String email){
//        UserResponse user=userResponseMapper.map(userRepository.findByEmail(email));
        UserResponse user=service.delete(email);
        return ResponseEntity.ok(user);
    }
//    @CrossOrigin(origins="http://localhost:4200")
//    @PutMapping("/{id}")
//    @Operation(summary="Modify User")
//    @ApiResponse(responseCode="200", description="succes")
//    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
//        if (service.exists(id)){
//            User u=service.findById(id);
//            u.setName(user.getName());
//            u.setEmail(user.getEmail());
//            u.setPassword(user.getPassword());
//            return ResponseEntity.ok(service.save(u));
//        }
//        else return ResponseEntity.notFound().build();
//    }
}
