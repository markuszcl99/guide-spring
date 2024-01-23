package com.markus.spring.test.service;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/23
 * @Description:
 */
public interface EmailService {
  /**
   * 发送邮件
   * @param message
   */
  public void sendEmail(String message);
}
