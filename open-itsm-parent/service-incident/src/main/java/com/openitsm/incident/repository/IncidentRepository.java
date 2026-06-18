package com.openitsm.incident.repository;

import com.openitsm.incident.domain.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    /* You dont have to write below query : (NATIVE SQL QUERY CALLED FROM findAll IN SERVICE LAYER)
    @Query(
            value = "SELECT INCIDENT_ID, INCIDENT_TITLE, INCIDENT_DESCRIPTION " +
                    "FROM ITSM_INCIDENT ORDER BY INCIDENT_ID",
            nativeQuery = true
    )
    List<Incident> findAllIncidents();
    */

    List<Incident> findAll();
}