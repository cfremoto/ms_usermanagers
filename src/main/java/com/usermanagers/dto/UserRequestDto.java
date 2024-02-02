package com.usermanagers.dto;

import com.usermanagers.model.Phone;
import com.usermanagers.model.User;
import com.usermanagers.utils.ValidationConstants;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserRequestDto {

    private String name;
    @Pattern(regexp = ValidationConstants.EMAIL_PATTERN, message = "The email does not have a valid format")
    private String email;
    @Pattern(regexp = ValidationConstants.PASSWORD_PATTERN, message = "The password does not have a valid format")
    private String password;
    private List<Phone> phones;

    public User toUser() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .phones(this.phones)
                .build();
    }
}
