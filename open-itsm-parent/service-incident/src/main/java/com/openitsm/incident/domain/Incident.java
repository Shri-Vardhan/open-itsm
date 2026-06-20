package com.openitsm.incident.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "ITSM_INCIDENT")
public class Incident {

    @Id
    @Column(name = "INCIDENT_ID")
    private Long id;

    @Column(name = "INCIDENT_TITLE")
    private String title;

    @Lob
    @Column(name = "INCIDENT_DESCRIPTION")
    private String description;

    public Incident() {
    }

    public Incident(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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