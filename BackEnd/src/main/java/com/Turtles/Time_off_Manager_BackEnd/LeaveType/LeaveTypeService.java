package com.Turtles.Time_off_Manager_BackEnd.LeaveType;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
@Service
public class LeaveTypeService {
    @Autowired
    private LeaveTypesRepository repo;
    public LeaveType save(LeaveType leavetype){
        return repo.save(leavetype);
    }
    public List<LeaveType> findAll(){
        return repo.findAll();
    }
    public LeaveType findById(int id){
        Optional<LeaveType> leavetype=repo.findById(id);
        if (leavetype.isPresent())
            return leavetype.get();
        else return null;
    }
    public void delete(int id){
        repo.deleteById(id);
    }
    public void update(LeaveType leavetype){
        repo.save(leavetype);
    }
}
