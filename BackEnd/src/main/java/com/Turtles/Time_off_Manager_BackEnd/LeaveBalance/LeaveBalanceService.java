package com.Turtles.Time_off_Manager_BackEnd.LeaveBalance;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import com.Turtles.Time_off_Manager_BackEnd.User.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
@Service
public class LeaveBalanceService {
    @Autowired
    private LeaveBalanceRepository repo;
    @Autowired
    private UserRepository userRepo;
    public LeaveBalance save(LeaveBalance leaveBalance) {
        return repo.save(leaveBalance);
    }
    public LeaveBalance findById(int id) {
        Optional<LeaveBalance> leaveBalance = repo.findById(id);
        if (leaveBalance.isPresent()) {
            return leaveBalance.get();
        }
        else return null;
    }
    public void delete(int id) {
        repo.deleteById(id);
    }
    public void update(LeaveBalance leaveBalance) {
        repo.save(leaveBalance);
    }
    public LeaveBalance findByUserId(int id) {
        Optional<User> user=userRepo.findById(id);
        if (user.isPresent()) {
            Optional<LeaveBalance> leaveBalance = repo.findByuser(user.get());
            if (leaveBalance.isPresent()) {
                return leaveBalance.get();
            } else return null;
        }
        return null;
    }
}
