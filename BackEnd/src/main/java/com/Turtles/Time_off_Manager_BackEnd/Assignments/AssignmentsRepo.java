package com.Turtles.Time_off_Manager_BackEnd.Assignments;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AssignmentsRepo extends JpaRepository<Assignments, Integer> {
    public List<Assignments> findByUser(int user);
    public List<Assignments> findByProject(int project);
}
