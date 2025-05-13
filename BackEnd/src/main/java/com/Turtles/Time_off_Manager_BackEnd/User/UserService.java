package com.Turtles.Time_off_Manager_BackEnd.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    @Autowired
    private UserRepository repo;
    public User save(User user){
        return repo.save(user);
    }
    public List<User> findAll(){
        return repo.findAll();
    }
    public User findById(int id){
        Optional<User> user = repo.findById(id);
        if (user.isPresent())
            return user.get();
        else return null;
    }
    public User findByName(String name){
        User a=repo.findByName(name);
        if (a!=null){
            return a;
        }
        return null;
    }
    public boolean exists(int id){
        return repo.existsById(id);
    }
    public void delete(int id){
        repo.deleteById(id);
    }
    public void update(User user){
        repo.save(user);
    }
}
