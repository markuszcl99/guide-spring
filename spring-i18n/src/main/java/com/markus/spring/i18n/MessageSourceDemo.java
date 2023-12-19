package com.markus.spring.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/19
 * @Description: {@link MessageSource}
 */
public class MessageSourceDemo {
  public static void main(String[] args) {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    // 添加资源包的基本名称，用于国际化消息的加载。
    messageSource.addBasenames("META-INF/messages");
    messageSource.setDefaultEncoding("UTF-8");

    String name = messageSource.getMessage("name", null, Locale.CHINA);
    System.out.println(name);
    name = messageSource.getMessage("name", null, Locale.ENGLISH);
    System.out.println(name);

    String hello = messageSource.getMessage("hello", null, Locale.CHINA);
    System.out.println(hello);
    hello = messageSource.getMessage("hello", null, Locale.ENGLISH);
    System.out.println(hello);

  }
}
