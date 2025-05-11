package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
@Service
public class TimeOffRequestsService {
    @Autowired
    private TimeOffRequestsRepository repo;
    @Autowired
    private UserRepository userRepo;
    public TimeOffRequest save(TimeOffRequest timeOffRequest) {
        User a=userRepo.findById(timeOffRequest.getUser().getUserId()).get();
        timeOffRequest.setUser(a);
        return repo.save(timeOffRequest);
    }
    public List<TimeOffRequest> findAll() {
    return repo.findAll();
    }
    public TimeOffRequest findById(int id){
        Optional <TimeOffRequest> optional = repo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        else return null;
    }
    public List<TimeOffRequest> findByUserId(User user){
        return repo.findByUser(user);
    }
    public void delete(int id){
        repo.deleteById(id);
    }
    public void update(TimeOffRequest timeOffRequest) {
        repo.save(timeOffRequest);
    }
}
