package com.usermanagers.service;

import com.usermanagers.dto.UserRequestDto;
import com.usermanagers.dto.UserResponseDto;
import com.usermanagers.exception.InternalServerErrorException;
import com.usermanagers.model.Phone;
import com.usermanagers.model.User;
import com.usermanagers.repository.PhoneRepository;
import com.usermanagers.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService JwtService;

    public void deleteUser(UUID id)  {
        log.info("Deleting user with id: {}", id);

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting user with id: {}", id);
            throw new InternalServerErrorException("Error deleting user with id: " + id + " - " + e.getMessage());
        }
    }

    public UserResponseDto createUser(UserRequestDto requestUser) {
        log.info("Creating user");

        userRepository.findByEmail(requestUser.getEmail())
                .ifPresent(user -> {
                    throw new RuntimeException("This email already exists: " + requestUser.getEmail());
                });

        User newUser = requestUser.toUser();
        newUser.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        newUser.setCreated(new Date());
        newUser.setLastLogin(new Date());
        newUser.setModified(null);
        newUser.setToken(JwtService.generateTokenUser(newUser));
        newUser.setActive(true);

        try {
            User savedUser = userRepository.save(newUser);
            return UserResponseDto.fromUser(savedUser);
        } catch (Exception e) {
            log.error("Error creating user");
            throw new InternalServerErrorException("Error creating user - " + e.getMessage());
        }
    }

    public UserResponseDto updateUser(UUID id, UserRequestDto requestUser) {
        log.info("Updating user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setName(requestUser.getName());
        user.setEmail(requestUser.getEmail());
        user.setModified(new Date());


        try {
            User savedUser = userRepository.save(user);
            updatePhones(savedUser, (Set<Phone>) requestUser.getPhones());
            return UserResponseDto.fromUser(savedUser);
        } catch (Exception e) {
            log.error("Error updating user with id: {}", id);
            throw new InternalServerErrorException("Error updating user with id: " + id + " - " + e.getMessage());
        }
    }

    public UserResponseDto getUser(UUID id) {
        log.info("Getting user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        return UserResponseDto.fromUser(user);
    }

    public UserResponseDto getUserByEmail(String email) {

        log.info("Getting user with email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        return UserResponseDto.fromUser(user);
    }

    public List<UserResponseDto> getAllUsers(){
        log.info("Getting all users");

        List<User> users = userRepository.findAll();

        return users.stream()
                    .map(UserResponseDto::fromUser)
                    .collect(Collectors.toList());
    }

    private void updatePhones(User user, Set<Phone> phones) {
        log.info("Updating phones for user with ID: {}", user.getId());

        phoneRepository.updatePhones(user.getId(), phones);
    }


}
