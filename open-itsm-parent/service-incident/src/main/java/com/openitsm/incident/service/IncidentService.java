package com.openitsm.incident.service;

import com.openitsm.incident.domain.Incident;
import com.openitsm.incident.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    public List<Incident> findAll() {
        return repository.findAll();
    }
}