package com.markus.spring.ioc.overview.domain;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/11/28
 * @Description: User对象持有
 */
public class UserHolder {
  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "UserHolder{" +
        "user=" + user +
        '}';
  }
}
