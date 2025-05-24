package com.Turtles.Time_off_Manager_BackEnd.web.transfer;

import com.Turtles.Time_off_Manager_BackEnd.Projects.Projects;
import com.Turtles.Time_off_Manager_BackEnd.Role.Role;

import java.util.List;
public class UserResponse {
    private String name;
    private String email;
    private String password;
//    private List<TimeOffRequestResponse> requests;
//    private ProjectResponse project;
    private Role role;
    //get and set
    public String getName() {return this.name;}
    public String getEmail() {return this.email;}
    public String getPassword() {return this.password;}
//    public List<TimeOffRequestResponse> getRequests() {return this.requests;}
//    public ProjectResponse getProject() {return this.project;}
    public Role getRole() {return this.role;}

    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
//    public void setRequests(List<TimeOffRequestResponse> requests) {this.requests = requests;}
//    public void setProject(ProjectResponse a) {
//        project=a;
//    }
//    public void setRole(String role) {this.role = role;}
    public void setRole(Role role) {this.role=role;}

//    public void addRequest(TimeOffRequestResponse request) {requests.add(request);}

    @Override
    public boolean equals(Object o) {
        if (o==null || this.getClass()!=o.getClass())
            return false;
        UserResponse user = (UserResponse)o;
        return this.name.equals(user.name) && this.email.equals(user.email) && this.password.equals(user.password);
    }
}
