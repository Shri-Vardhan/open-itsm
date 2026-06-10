package com.openitsm.incident.repository;

import com.openitsm.incident.domain.Incident;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IncidentRepository {

    private final JdbcTemplate jdbcTemplate;

    public IncidentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Incident> findAll() {
        String sql = """
            SELECT INCIDENT_ID,
                   INCIDENT_TITLE,
                   INCIDENT_DESCRIPTION
            FROM ITSM_INCIDENT
            ORDER BY INCIDENT_ID
            """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Incident incident = new Incident();
            incident.setId(rs.getLong("INCIDENT_ID"));
            incident.setTitle(rs.getString("INCIDENT_TITLE"));
            incident.setDescription(rs.getString("INCIDENT_DESCRIPTION"));
            return incident;
        });
    }
}