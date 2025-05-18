package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserRepository;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateTimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateUserRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.TimeOffRequestResponse;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TimeOffRequestsService {
    @Autowired
    private TimeOffRequestsRepository repo;
    @Autowired
    private UserRepository userRepo;
    private final TimeOffRequestMapper mapper = new TimeOffRequestMapper();
    private final TimeOffRequestResponseMapper responseMapper = new TimeOffRequestResponseMapper();
    public TimeOffRequestResponse save(CreateTimeOffRequest timeOffRequest) {
        TimeOffRequest request=mapper.map(timeOffRequest);
        repo.save(request);
        TimeOffRequestResponse a=responseMapper.map(request);
        return a;
//        timeOffRequest.setUser(user);
//        user.addRequest(timeOffRequest);

    }
    public List<TimeOffRequestResponse> findAll() {
    return repo.findAll().stream().map(responseMapper::map).toList();
    }
    public List<TimeOffRequestResponse> findByUser(String email){
//        Optional <TimeOffRequest> optional = repo.findById(id);
//        if (optional.isPresent()) {
//            return optional.get();
//        }
//        else return null;
        Optional<User> user=userRepo.findByEmail(email);
        List<TimeOffRequestResponse> a=new ArrayList<>();
        if (user.isEmpty()){
            return a;
        }
        return repo.findByUser(user.get()).stream().map(responseMapper::map).toList();
    }
//    public List<TimeOffRequest> findByUserId(User user){
//        return repo.findByUser(user);
//    }
    public void delete(CreateTimeOffRequest timeOffRequest) {
        TimeOffRequest request=mapper.map(timeOffRequest);
        repo.delete(request);
    }
//    public void update(CreateTimeOffRequest timeOffRequest) {
//        repo.save(timeOffRequest);
//    }
}
