package com.flexautopecas.flexecommerce.dto;

import com.flexautopecas.flexecommerce.entities.Role;
import com.flexautopecas.flexecommerce.entities.User;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    private Long id;
    @NotBlank(message = "{user.name-validation}")
    private String firstName;
    @NotBlank(message = "{user.lastName-validation}")
    private String lastName;
    @NotBlank(message = "{user.email-validation}")
    private String email;

    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(){

    }

    public UserDTO(User entity){
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        email = entity.getEmail();

        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }

    public UserDTO(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void addRole(RoleDTO role){
        this.roles.add(role);
    }
}
