package com.usermanagers.repository;

import com.usermanagers.dto.UserRequestDto;
import com.usermanagers.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor< UUID> {
    default Optional<User> findByEmailWithlog(String email) {
        return findByEmail(email);
    }

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);
}
