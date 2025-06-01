package com.Turtles.Time_off_Manager_BackEnd.User;

import com.Turtles.Time_off_Manager_BackEnd.Projects.ProjectsService;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.AdminUpdateUserRequest; // Added import
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateUserRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.ProjectResponse;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.TimeOffRequestResponse;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.UserResponse;
// Removed import for UserNotFoundException for now

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repo;
    //    @Autowired
    //    private ProjectsService projectsService;
    private final UserMapper userMapper = new UserMapper();
    private final UserResponseMapper userResponseMapper = new UserResponseMapper();

    public User save(CreateUserRequest createUserRequest) {
        User user = userMapper.map(createUserRequest);
        repo.save(user);
        return user;
    }

    public List<UserResponse> findAll() {
        return repo.findAll().stream().map(userResponseMapper::map).toList();
    }

    public UserResponse findById(Long id) {
        Optional<User> user = repo.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        return userResponseMapper.map(user.get());
    }

    public UserResponse findByName(String name) {
        Optional<User> user = repo.findByName(name);
        if (user.isEmpty()) {
            return null;
        }
        return userResponseMapper.map(user.get());
    }

    public UserResponse findByEmail(String email) {
        Optional<User> user = repo.findByEmail(email);
        if (user.isEmpty()) {
            return null;
        }
        return userResponseMapper.map(user.get());
    }

    public UserResponse modify(String email, CreateUserRequest createUserRequest) {
        Optional<User> userOptional = repo.findByEmail(email);
        if (userOptional.isEmpty()) {
            return null;
        } else {
            User user1 = userOptional.get();
            user1.setEmail(createUserRequest.getEmail());
            user1.setPassword(createUserRequest.getPassword());
            user1.setName(createUserRequest.getName());
            repo.save(user1);
            return userResponseMapper.map(user1);
        }
    }

    public boolean exists(String email) {
        return repo.existsByEmail(email);
    }

    public UserResponse delete(String email) {
        Optional<User> userOptional = repo.findByEmail(email);
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        repo.delete(user);
        return userResponseMapper.map(user);
    }

    public void update(CreateUserRequest createUserRequest) {
        User user = userMapper.map(createUserRequest);
        repo.save(user);
    }

    public User findRawByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    public UserResponse adminUpdateUser(Long userId, AdminUpdateUserRequest updateRequest) {
        User userToUpdate = repo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId + " for admin update."));

        userToUpdate.setName(updateRequest.getName());

        if (!userToUpdate.getEmail().equalsIgnoreCase(updateRequest.getEmail()) && repo.existsByEmail(updateRequest.getEmail())) {
            throw new IllegalArgumentException("Email '" + updateRequest.getEmail() + "' is already in use by another account.");
        }
        userToUpdate.setEmail(updateRequest.getEmail());
        userToUpdate.setRole(updateRequest.getRole());

        User updatedUser = repo.save(userToUpdate);
        return userResponseMapper.map(updatedUser);
    }

//    public List<TimeOffRequestResponse> getRequests(String email){
//        UserResponse a=findByEmail(email);
//        return a.getRequests();
//    }

//    public ProjectResponse getProjects(String email){
//        User a=findRawByEmail(email);
//        return projectsService.findByManager(a);
//    }
}