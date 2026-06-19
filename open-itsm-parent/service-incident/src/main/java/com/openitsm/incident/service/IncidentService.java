package com.openitsm.incident.service;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import java.util.List;
import com.openitsm.incident.domain.Incident;
import com.openitsm.incident.repository.IncidentRepository;

@Service
public class IncidentService {

    private final IncidentRepository repository;
    private static final Logger log =
            LogManager.getLogger(IncidentService.class);

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    /*
    // NATIVE-SQL (OUR OWN CUSTOM SQL)
    public List<Incident> findAll() {
        return repository.findAllIncidents();
    */

    public List<Incident> findAll() {
        log.debug("Fetching incidents from repository");
        return repository.findAll();
    }
}