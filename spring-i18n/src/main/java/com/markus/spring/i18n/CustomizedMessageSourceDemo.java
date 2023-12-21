package com.markus.spring.i18n;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/20
 * @Description:
 */
@EnableAutoConfiguration
public class CustomizedMessageSourceDemo {

//  @Bean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("META-INF/messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext =
        new SpringApplicationBuilder(CustomizedMessageSourceDemo.class)
            .web(WebApplicationType.NONE)
            .run(args);

    MessageSource messageSource = applicationContext.getBean(MessageSource.class);
    System.out.println(messageSource);

    String introduce = messageSource.getMessage("introduce", new Object[]{"张某某", 24}, Locale.ENGLISH);
    System.out.println(introduce);

    introduce = messageSource.getMessage("introduce", new Object[]{"张某某", 24}, Locale.CHINA);
    System.out.println(introduce);

    applicationContext.close();
  }
}
