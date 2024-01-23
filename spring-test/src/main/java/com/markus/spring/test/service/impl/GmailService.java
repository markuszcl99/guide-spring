package com.markus.spring.test.service.impl;

import com.markus.spring.test.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/23
 * @Description:
 */
@Service
public class GmailService implements EmailService {

  @Autowired
  private Environment env;

  @Override
  public void sendEmail(String message) {
    boolean isEmailEnabled = Boolean.parseBoolean(env.getProperty("service.email.enabled"));
    if (isEmailEnabled) {
      // 发送电子邮件的逻辑
      System.out.println("Sending email : " + message);
    } else {
      System.out.println("Email service is disabled");
    }
  }
}
