package com.Turtles.Time_off_Manager_BackEnd.web.transfer;

import com.Turtles.Time_off_Manager_BackEnd.Projects.Projects;
import com.Turtles.Time_off_Manager_BackEnd.Role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
public class CreateUserRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private Role role;
//    List<CreateTimeOffRequest>requests;
//    CreateProjectRequest project;



    public void setName(String a){
        this.name = a;
    }
    public void setEmail(String a){
        this.email = a;
    }
    public void setPassword(String a){
        password=a;
    }
//    public void setRequests(List<CreateTimeOffRequest> a){
//        requests=a;
//    }
//    public void setProject(CreateProjectRequest a) {
//        project=a;
//    }
    public void setRole(Role a){
        this.role=a;
    }
    public String getName(){return this.name;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
//    public List<CreateTimeOffRequest> getRequests(){return this.requests;}
    public Role getRole(){return role;}
//    public void addRequest(CreateTimeOffRequest a){
//        this.requests.add(a);
//    }
//    public CreateProjectRequest getProject() {
//        return project;
//    }

    @Override
    public boolean equals(Object o) {
        if (null == o || this.getClass()!=o.getClass())  return false;
        CreateUserRequest that = (CreateUserRequest) o;
        return this.name.equals(that.name) && this.email.equals(that.email) && this.password.equals(that.password) && this.role==that.role;
    }
}
