package com.markus.spring.ioc.overview.domain;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/11/27
 * @Description:
 */
public class User {
  private Long id;
  private String username;

  public User(){
    System.out.println("开始初始化");
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        '}';
  }
}
