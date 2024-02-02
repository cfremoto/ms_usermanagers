package com.usermanagers.dto;

import com.usermanagers.model.Phone;
import com.usermanagers.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class UserResponseDto {
    private UUID id;
    private String name;
    private String email;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private boolean isActive;
    private String token;
    private List<Phone> phones;

    public static UserResponseDto fromUser(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .isActive(user.isActive())
                .token(user.getToken())
                .phones(user.getPhones())
                .build();
    }
}
