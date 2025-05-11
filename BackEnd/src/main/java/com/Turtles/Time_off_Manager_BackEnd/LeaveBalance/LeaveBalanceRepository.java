package com.Turtles.Time_off_Manager_BackEnd.LeaveBalance;

import com.Turtles.Time_off_Manager_BackEnd.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Integer>{
    public Optional<LeaveBalance> findByuser(User user);
}
