package com.markus.spring.test;

import com.markus.spring.test.service.EmailService;
import com.markus.spring.test.service.impl.GmailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.mock.env.MockPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/1/23
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GmailService.class})
public class EmailServiceTestWithMockPropertySource {
  @Autowired
  private EmailService emailService;
  @Autowired
  private ConfigurableEnvironment env;

  @Before
  public void setup() {
    MockPropertySource propertySource = new MockPropertySource();
    propertySource.setProperty("service.email.enabled", "true");
    env.getPropertySources().addFirst(propertySource);
  }

  @Test
  public void testSendEmail() {
    emailService.sendEmail("Hello,Spring Test World!");
  }
}
