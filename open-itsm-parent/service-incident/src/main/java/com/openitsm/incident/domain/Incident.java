package com.openitsm.incident.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "INCIDENT")
public class Incident {

    @Id
    @Column(name = "INCIDENT_ID", length = 30)
    private String id;

    @Column(name = "INCIDENT_TITLE", nullable = false, length = 255)
    private String title;

    @Lob
    @Column(name = "INCIDENT_DESCRIPTION")
    private String description;

    // Default Constructor
    public Incident() {
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}