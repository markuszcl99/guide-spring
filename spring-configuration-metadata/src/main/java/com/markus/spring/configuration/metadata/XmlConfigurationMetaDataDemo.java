package com.markus.spring.configuration.metadata;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/8
 * @Description: Xml配置元数据示例
 */
public class XmlConfigurationMetaDataDemo {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/beans.xml");
    System.out.println("Spring 容器已经启动");

    User user = context.getBean(User.class);

    context.close();
    System.out.println("Spring 容器已经启动");
  }
}
