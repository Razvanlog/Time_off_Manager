package com.Turtles.Time_off_Manager_BackEnd.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    boolean existsByName(String name);
}
