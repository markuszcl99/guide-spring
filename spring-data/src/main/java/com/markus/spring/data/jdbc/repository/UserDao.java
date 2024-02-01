package com.markus.spring.data.jdbc.repository;

import com.markus.spring.data.jdbc.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author: markus
 * @date: 2024/2/1 11:09 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 增
     *
     * @param user
     */
    public void insertUser(User user) {
        String sql = "insert into user (name,age,address) values (?,?,?);";
        jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getAddress());
    }

    /**
     * 删
     */
    public void deleteUser(int id) {
        String sql = "delete from user where id = ?;";
        jdbcTemplate.update(sql, id);
    }

    /**
     * 改
     */
    public void updateUser(User user) {
        String sql = "update user set name=?,age=?,address=? where id = ?;";
        jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getAddress(), user.getId());
    }

    /**
     * 查
     */
    public User queryUser(int id) {
        String sql = "select * from user where id = ?;";
        return jdbcTemplate.queryForObject(sql, User.class);
    }

}
