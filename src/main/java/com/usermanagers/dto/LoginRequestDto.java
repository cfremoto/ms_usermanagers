package com.usermanagers.dto;

import com.usermanagers.utils.ValidationConstants;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDto {
    @Pattern(regexp = ValidationConstants.EMAIL_PATTERN, message = "The email does not have a valid format")
    private String email;
    @Pattern(regexp = ValidationConstants.PASSWORD_PATTERN, message = "The password does not have a valid format")
    private String password;

}
