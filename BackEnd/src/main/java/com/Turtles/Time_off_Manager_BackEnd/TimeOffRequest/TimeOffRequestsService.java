package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserRepository;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateTimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateUserRequest;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.TimeOffRequestResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TimeOffRequestsService {
    private final TimeOffRequestsRepository repo;
    private final UserRepository userRepo;
    private final TimeOffRequestMapper mapper;
    private final TimeOffRequestResponseMapper responseMapper;

    @Autowired
    public TimeOffRequestsService(TimeOffRequestsRepository repo,
                                  UserRepository userRepo,
                                  TimeOffRequestMapper mapper,
                                  TimeOffRequestResponseMapper responseMapper) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.mapper = mapper;
        this.responseMapper = responseMapper;
    }

    public TimeOffRequestResponse save(CreateTimeOffRequest createDto) {
        Long userId = createDto.getUserId();
        if (userId == null) {
            throw new IllegalArgumentException("User ID must be provided in the request.");
        }
        User userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        TimeOffRequest requestEntity = mapper.map(createDto, userEntity);

        if (requestEntity.getEndDate().isBefore(requestEntity.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }

        TimeOffRequest savedEntity = repo.save(requestEntity);
        return responseMapper.map(savedEntity);
    }
    public List<TimeOffRequestResponse> findAll() {
    return repo.findAll().stream().map(responseMapper::map).toList();
    }
    public List<TimeOffRequestResponse> findByUser(String email){

        Optional<User> user=userRepo.findByEmail(email);
        List<TimeOffRequestResponse> a=new ArrayList<>();
        if (user.isEmpty()){
            return a;
        }
        return repo.findByUser(user.get()).stream().map(responseMapper::map).toList();
    }

//    public void delete(CreateTimeOffRequest timeOffRequest) {
//        TimeOffRequest request=mapper.map(timeOffRequest);
//        repo.delete(request);
//    }

}
