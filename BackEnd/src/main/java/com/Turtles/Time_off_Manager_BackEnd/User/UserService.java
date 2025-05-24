package com.Turtles.Time_off_Manager_BackEnd.User;
import com.Turtles.Time_off_Manager_BackEnd.Projects.ProjectsService;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateUserRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.ProjectResponse;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.TimeOffRequestResponse;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService{

    @Autowired
    private UserRepository repo;
//    @Autowired
//    private ProjectsService projectsService;
    private final UserMapper userMapper=new UserMapper();
    private final UserResponseMapper userResponseMapper=new UserResponseMapper();

//    UserService(UserMapper userMapper, UserResponseMapper userResponseMapper) {
//        this.userMapper = userMapper;
//        this.userResponseMapper = userResponseMapper;
//    }

    public User save(CreateUserRequest createUserRequest) {
        User user=userMapper.map(createUserRequest);
        repo.save(user);
        return user;
    }
    public List<UserResponse> findAll(){
        return repo.findAll().stream().map(userResponseMapper::map).toList();
    }
    public UserResponse findById(Long id){
        Optional<User> user = repo.findById(id);
        if (user.isEmpty())
            return null;
        else return userResponseMapper.map(user.get());
    }
    public UserResponse findByName(String name){
        Optional<User> user = repo.findByName(name);
        if (user.isEmpty()){
            return null;
        }
        return userResponseMapper.map(user.get());
    }
    public UserResponse findByEmail(String email){
        Optional<User> user = repo.findByEmail(email);
        if (user.isEmpty()){
            return null;
        }
        return userResponseMapper.map(user.get());
    }
    public UserResponse modify(String email, CreateUserRequest createUserRequest){
        Optional<User> user = repo.findByEmail(email);
        if (user.isEmpty()){
            return null;
        }
        else{
            User user1=user.get();
            user1.setEmail(createUserRequest.getEmail());
            user1.setPassword(createUserRequest.getPassword());
            user1.setName(createUserRequest.getName());
            repo.save(user1);
            return userResponseMapper.map(user1);
        }
    }
    public boolean exists(String email){
        return repo.existsByEmail(email);
    }
    public UserResponse delete(String email){
        Optional<User> user=repo.findByEmail(email);
        if (user.isEmpty()){
            return null;
        }
        repo.delete(user.get());
        return userResponseMapper.map(user.get());
//        int id=repo.findByEmail(email).getUserId();
//        repo.deleteById(id);
    }
    public void update(CreateUserRequest createUserRequest){
        User user=userMapper.map(createUserRequest);
        repo.save(user);
    }
    public User findRawByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
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
