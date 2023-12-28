package com.markus.spring.conversion;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditor;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/28
 * @Description: spring 整合自定义 {@link PropertyEditor}
 * @see PropertyEditor
 */
public class SpringCustomizedPropertyEditorDemo {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/property-editor-context.xml");

    User user = context.getBean(User.class);
    System.out.println(user);

    context.close();
  }
}
