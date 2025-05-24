package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserRepository;
import com.Turtles.Time_off_Manager_BackEnd.web.transfer.CreateTimeOffRequest;
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
        String userEmail = createDto.getUserEmail();
        if (userEmail == null) {
            throw new IllegalArgumentException("User ID must be provided in the request.");
        }
        User userEntity = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + userEmail));
        TimeOffRequest requestEntity = mapper.map(createDto, userEntity);
        requestEntity.setRequestUserNumber((long)findByUser(createDto.getUserEmail()).size());
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
    public TimeOffRequestResponse findByUserAndNr(String email,long nr){
        Optional<User> user=userRepo.findByEmail(email);
        if (user.isEmpty()){
            return null;
        }
        Optional<TimeOffRequest> t=repo.findByUserEmailAndRequestUserNumber(email,nr);
        if (t.isEmpty()){return null;}
        return responseMapper.map(t.get());
//        TimeOffRequestResponse t=responseMapper.map();
    }
    public TimeOffRequestResponse delete(String email,long nr){
        Optional<User> user=userRepo.findByEmail(email);
        if (user.isEmpty()){return null;}
        Optional<TimeOffRequest> t=repo.findByUserEmailAndRequestUserNumber(email,nr);
        if (t.isEmpty()){return null;}
        repo.delete(t.get());
        return responseMapper.map(t.get());
    }
    public TimeOffRequestResponse update(String email,long nr,CreateTimeOffRequest createDto){
        Optional<User> user=userRepo.findByEmail(email);
        if (user.isEmpty()){return null;}
        Optional<TimeOffRequest> t=repo.findByUserEmailAndRequestUserNumber(email,nr);
        if (t.isEmpty()){return null;}
        TimeOffRequest toModifyEntity=t.get();
        toModifyEntity.setDescription(createDto.getDescription());
        toModifyEntity.setEndDate(createDto.getEnd());
        toModifyEntity.setStartDate(createDto.getStart());
        toModifyEntity.setLeaveType(toModifyEntity.getLeaveType());
        toModifyEntity.setStartDate(toModifyEntity.getStartDate());
        toModifyEntity.setRequestedDays(toModifyEntity.getRequestedDays());
        repo.save(toModifyEntity);
        return responseMapper.map(toModifyEntity);
    }
//    public void delete(CreateTimeOffRequest timeOffRequest) {
//        TimeOffRequest request=mapper.map(timeOffRequest);
//        repo.delete(request);
//    }

}
