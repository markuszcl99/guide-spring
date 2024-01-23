package com.markus.spring.test;

import com.markus.spring.test.service.EmailService;
import com.markus.spring.test.service.impl.GmailService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/23
 * @Description:
 */
public class EmailServiceTestWithEnvironment {
  private EmailService emailService;
  private MockEnvironment mockEnvironment;

  @Before
  public void setup() {
    mockEnvironment = new MockEnvironment();
    mockEnvironment.withProperty("service.email.enabled", "true");

    emailService = new GmailService();
    // 使用反射将 env 对象注入到 emailService 对象中去
    ReflectionTestUtils.setField(emailService, "env", mockEnvironment);
  }

  @Test
  public void testSendEmail() {
    emailService.sendEmail("Hello,Spring Test World!");
  }
}
