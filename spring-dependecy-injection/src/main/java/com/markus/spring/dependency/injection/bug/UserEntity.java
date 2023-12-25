package com.markus.spring.dependency.injection.bug;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/25
 * @Description:
 */
public class UserEntity {
  private String username;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "UserEntity{" +
        "username='" + username + '\'' +
        '}';
  }
}
