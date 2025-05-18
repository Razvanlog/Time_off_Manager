package com.Turtles.Time_off_Manager_BackEnd.Projects;
import com.Turtles.Time_off_Manager_BackEnd.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectsRepository extends JpaRepository<Projects, Integer> {
    Optional<Projects> findByName(String name);
    Optional<Projects> findByManager(User user);
}
