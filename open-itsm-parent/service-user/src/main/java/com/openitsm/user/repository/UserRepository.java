package com.openitsm.user.repository;

import com.openitsm.user.model.AppUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private static final Logger logger = LogManager.getLogger(UserRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    //Find User By Username
    public Optional<AppUser> findByUsername(String username) {

        logger.debug("Looking up user in database: {}", username);

        String sql = """
                SELECT USER_ID,
                       USERNAME,
                       PASSWORD,
                       ROLE,
                       ENABLED
                FROM USERS
                WHERE USERNAME = ?
                """;

        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                logger.debug("User record found for {}", username);
                AppUser user = new AppUser();
                user.setId(rs.getLong("USER_ID"));
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setRole(rs.getString("ROLE"));
                user.setEnabled(rs.getString("ENABLED"));

                return Optional.of(user);
            }
            logger.warn("No user record found for {}", username);
            return Optional.empty();
        }, username);
    }

    // INSERT USER (create-user flow)
    public void save(AppUser user) {

        String sql = """
                INSERT INTO USERS
                (USER_ID, USERNAME, PASSWORD, ROLE, ENABLED)
                VALUES (USER_SEQ.NEXTVAL, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getRole(), user.getEnabled());
    }
}