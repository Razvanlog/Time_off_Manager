package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TimeOffRequestsRepository  extends JpaRepository<TimeOffRequest, Integer> {
    public List<TimeOffRequest> findByUser(User user);
}
