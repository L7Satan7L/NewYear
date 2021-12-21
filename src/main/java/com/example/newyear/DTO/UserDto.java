package com.example.newyear.DTO;

import com.example.newyear.annotation.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
public class UserDto {

    @NotNull
    @NotEmpty(message = "username can't be empty")
    private String name;

    @NotNull
    @NotEmpty(message = "name can't be empty")
    private String firstName;

    @NotNull
    @NotEmpty(message = "password can't be empty")
    private String password;
    private String matchingPassword;

}
