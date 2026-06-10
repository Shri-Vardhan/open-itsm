package com.openitsm.incident.service;

import com.openitsm.incident.repository.IncidentRepository;
import org.springframework.stereotype.Service;

@Service
public class IncidentService {

    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

}

