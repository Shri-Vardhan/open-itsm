package com.openitsm.user.repository;

import com.openitsm.user.model.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<AppUser> findByUsername(String username) {

        String sql = """
        SELECT USER_ID,
               USERNAME,
               PASSWORD,
               ROLE,
               ENABLED
        FROM USERS
        WHERE USERNAME = ?
        """;

        try {
            AppUser user = jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> {
                        AppUser u = new AppUser();
                        u.setId(rs.getLong("USER_ID"));
                        u.setUsername(rs.getString("USERNAME"));
                        u.setPassword(rs.getString("PASSWORD"));
                        u.setRole(rs.getString("ROLE"));
                        u.setEnabled("Y".equals(rs.getString("ENABLED")));
                        return u;
                    },
                    username
            );

            return Optional.ofNullable(user);

        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}