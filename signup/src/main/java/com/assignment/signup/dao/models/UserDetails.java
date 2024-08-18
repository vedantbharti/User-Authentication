package com.assignment.signup.dao.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    private String email;
    private String firstName;
    private String lastName;
    private String contact;
    @JsonIgnore
    private String password;

}
