package com.markus.spring.transaction.service;

import com.markus.spring.data.jdbc.domain.entity.User;
import com.markus.spring.transaction.config.TransactionModuleApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/2
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TransactionModuleApplicationConfig.class)
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void testProcessUser() {
    userService.processUser();
  }

  @Test
  public void testQueryAllUsers() {
    List<User> users = userService.queryAllUsers();
    users.forEach(System.out::println);
  }
}
