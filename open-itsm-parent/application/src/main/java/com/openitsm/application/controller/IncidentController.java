package com.openitsm.application.controller;

import com.openitsm.incident.domain.Incident;
import com.openitsm.incident.service.IncidentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IncidentController {

    private final IncidentService service;
    private static final Logger logger = LogManager.getLogger(IncidentController.class);
    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @GetMapping("/api/incidents")
    public List<Incident> findAll() {
        logger.info("Info - Retrieving all incidents");
        logger.error("Error - Retrieving all incidents");
        logger.debug("Debug - Retrieving all incidents");
        logger.fatal("Fatal - Retrieving all incidents");
        return service.findAll();
    }
}