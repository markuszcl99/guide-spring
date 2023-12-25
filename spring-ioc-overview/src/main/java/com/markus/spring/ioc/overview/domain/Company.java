package com.markus.spring.ioc.overview.domain;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/25
 * @Description:
 */
public class Company {
  private String companyName;

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  @Override
  public String toString() {
    return "Company{" +
        "companyName='" + companyName + '\'' +
        '}';
  }
}
