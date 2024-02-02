package com.markus.spring.data.jdbc.repository;

import com.markus.spring.data.jdbc.domain.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
  @Nullable
  public User queryUser(int id) {
    String sql = "select * from user where id = ?;";
    List<User> userList = jdbcTemplate.query(sql, new Integer[]{id}, new RowMapper<User>() {
      @Override
      public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setAge(rs.getInt("age"));
        user.setName(rs.getString("name"));
        user.setAddress(rs.getString("address"));
        return user;
      }
    });
    if (CollectionUtils.isEmpty(userList)) {
      return null;
    }
    return userList.get(0);
  }

  @Nullable
  public User queryUserByName(String name) {
    String sql = "select * from user where name = ?;";
    List<User> userList = jdbcTemplate.query(sql, new String[]{name}, new RowMapper<User>() {
      @Override
      public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setAge(rs.getInt("age"));
        user.setName(rs.getString("name"));
        user.setAddress(rs.getString("address"));
        return user;
      }
    });
    if (CollectionUtils.isEmpty(userList)) {
      return null;
    }
    return userList.get(0);
  }

  public List<User> queryUsers(int id) {
    String sql = "select * from user where id >= ?;";
    List<User> userList = jdbcTemplate.query(sql, new Integer[]{id}, new RowMapper<User>() {
      @Override
      public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setAge(rs.getInt("age"));
        user.setName(rs.getString("name"));
        user.setAddress(rs.getString("address"));
        return user;
      }
    });
    return userList;
  }

}
