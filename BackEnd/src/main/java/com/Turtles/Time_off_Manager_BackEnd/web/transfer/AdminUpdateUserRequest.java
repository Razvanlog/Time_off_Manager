package com.Turtles.Time_off_Manager_BackEnd.web.transfer;

import com.Turtles.Time_off_Manager_BackEnd.Role.Role; // Ensure this path is correct for your Role enum
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank; // For String: not null and not just whitespace
import jakarta.validation.constraints.NotNull;  // For Objects/Enums: not null

// Optional Lombok annotations for getters/setters
// import lombok.Getter;
// import lombok.Setter;
// import lombok.NoArgsConstructor; // If you want a no-args constructor with Lombok

// @Getter
// @Setter
// @NoArgsConstructor
public class AdminUpdateUserRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Role cannot be null")
    private Role role;

    // --- Getters and Setters (required if not using Lombok) ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}