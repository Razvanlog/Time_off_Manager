package com.Turtles.Time_off_Manager_BackEnd.TimeOffRequest;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;
import java.util.Optional;
public interface TimeOffRequestsRepository  extends JpaRepository<TimeOffRequest, Long> {
    public List<TimeOffRequest> findByUser(User user);
    Optional<TimeOffRequest> findByUserEmailAndRequestUserNumber(String userEmail, long RequestUserNumber);

}
