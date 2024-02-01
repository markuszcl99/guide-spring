package com.markus.spring.data.jdbc;

import com.markus.spring.data.jdbc.config.ApplicationConfiguration;
import com.markus.spring.data.jdbc.domain.entity.User;
import com.markus.spring.data.jdbc.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: markus
 * @date: 2024/2/1 11:29 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestJdbcTemplate {
  @Autowired
  private UserDao userDao;

  @Test
  public void testInsertUser() {
    User user = new User();
    user.setName("Luna");
    user.setAge(24);
    user.setAddress("山东菏泽");
    userDao.insertUser(user);
  }

  @Test
  public void testQueryUser() {
    User user = userDao.queryUser(5);
    System.out.println(user);
  }
}
