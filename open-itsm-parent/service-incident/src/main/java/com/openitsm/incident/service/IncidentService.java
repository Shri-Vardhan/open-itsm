package com.openitsm.incident.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import com.openitsm.incident.domain.Incident;
import com.openitsm.incident.repository.IncidentRepository;

@Service
public class IncidentService {

    private final IncidentRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(IncidentService.class);

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    /*
    // NATIVE-SQL (OUR OWN CUSTOM SQL)
    public List<Incident> findAll() {
        return repository.findAllIncidents();
    */

    public List<Incident> findAll() {
        logger.debug("Fetching incidents from repository");
        return repository.findAll();
    }
}