package com.openitsm.incident.repository;

import com.openitsm.incident.domain.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository
        extends JpaRepository<Incident, Long> {

}
