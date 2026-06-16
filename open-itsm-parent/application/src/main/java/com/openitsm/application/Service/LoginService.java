package com.openitsm.application.Service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final JdbcTemplate jdbcTemplate;

    public LoginService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean authenticate(String username, String password) {

        String sql = """
            SELECT COUNT(*)
            FROM USERS
            WHERE USERNAME = ?
            AND PASSWORD = ?
            """;

        Number count = jdbcTemplate.queryForObject(sql, Number.class, username, password);

        System.out.println("RAW COUNT = " + count);

        return count != null && count.intValue() == 1;
    }
}
