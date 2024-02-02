package com.usermanagers.repository;

import com.usermanagers.model.Phone;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, UUID>, JpaSpecificationExecutor<UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.phones = :phones WHERE u.id = :id")
    void updatePhones(@Param("id") UUID id, @Param("phones") Set<Phone> phones);

}
