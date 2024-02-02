package com.usermanagers.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private UUID id;
    private String name;
    private String password;
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phones;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean isActive;

}
