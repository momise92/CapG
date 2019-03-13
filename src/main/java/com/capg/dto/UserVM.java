package com.capg.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserVM extends UserDto {

    @NotBlank
    @Size(min=8)
    private String password;

    @Size(min=8)
    private String checkPassword;

    public UserVM() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
