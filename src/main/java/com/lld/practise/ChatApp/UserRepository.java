package com.lld.practise.ChatApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertUser(User newUser) {
        String query = "INSERT into users (user_name,file_name) VALUES (?,?)";
        jdbcTemplate.update(query, newUser.getUserName(), newUser.getFileName());
    }

    public String getFileName(String userName) {
        String query = "SELECT file_name FROM users where user_name = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{userName}, String.class);
    }

}
