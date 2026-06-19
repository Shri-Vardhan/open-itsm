package com.openitsm.user.model;

import jakarta.persistence.*;

@Entity
@Table(name = "USERS")
public class AppUser {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq")
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "USER_SEQ",
            allocationSize = 1)

    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ROLE", nullable = false)
    private String role;

    @Column(name = "ENABLED")
    private String enabled;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getEnabled() {
        return enabled;
    }
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
    public boolean isEnabledFlag() {
        return "Y".equalsIgnoreCase(enabled);
    }
}