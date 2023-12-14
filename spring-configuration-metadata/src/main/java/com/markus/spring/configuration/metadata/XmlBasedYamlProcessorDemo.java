package com.markus.spring.configuration.metadata;

import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/14
 * @Description: 基于 xml 的 {@link YamlProcessor}
 */
public class XmlBasedYamlProcessorDemo {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/xml-yaml-processor-context.xml");

    Map<String, Object> yamlMapFactoryBean = context.getBean("yamlMapFactoryBean", Map.class);
    System.out.println(yamlMapFactoryBean);

    context.close();
  }
}
