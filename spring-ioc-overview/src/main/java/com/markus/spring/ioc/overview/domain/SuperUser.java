package com.markus.spring.ioc.overview.domain;

import com.markus.spring.ioc.overview.annotation.Super;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/11/28
 * @Description:
 */
@Super
public class SuperUser extends User {
  private String address;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "SuperUser{" +
        "address='" + address + '\'' +
        "} " + super.toString();
  }
}
