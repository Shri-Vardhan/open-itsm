package com.openitsm.user.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "USERS",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "USERNAME"
                )
        }
)
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq"
    )
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "USER_SEQ",
            allocationSize = 1
    )
    @Column(name = "USER_ID")
    private Long userId;


    @Column(
            name = "USERNAME",
            nullable = false
    )
    private String username;


    @Column(
            name = "PASSWORD",
            nullable = false
    )
    private String password;


    @Column(name = "ENABLED")
    private String enabled;


    public User() {
    }


    public User(
            Long userId,
            String username,
            String password,
            String enabled
    ) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }


    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getEnabled() {
        return enabled;
    }


    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}