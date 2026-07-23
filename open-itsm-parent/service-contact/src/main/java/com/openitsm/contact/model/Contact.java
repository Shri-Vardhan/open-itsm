package com.openitsm.contact.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CONTACT")
public class Contact {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_seq"
    )
    @SequenceGenerator(
            name = "contact_seq",
            sequenceName = "CONTACT_SEQ",
            allocationSize = 1
    )
    @Column(name = "CONTACT_ID")
    private Long id;

    @Column(
            name = "USERNAME",
            unique = true,
            nullable = false
    )
    private String username;

    @Column(
            name = "PASSWORD",
            nullable = false
    )
    private String password;

    @Column(
            name = "ENABLED",
            nullable = false
    )
    private String enabled;

    public Contact() {
    }

    public boolean isEnabledFlag() {
        return "Y".equalsIgnoreCase(enabled);
    }

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

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
