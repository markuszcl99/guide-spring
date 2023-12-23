package com.markus.spring.validation;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: markus
 * @date: 2023/12/23 11:31 PM
 * @Description: Spring Validator 示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 * @see Validator
 * @see MethodValidationPostProcessor
 * @see LocalValidatorFactoryBean
 */
public class SpringBeanValidatorDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");

        UserBeanProcessor userBeanProcessor = context.getBean(UserBeanProcessor.class);
        UserBean userBean = new UserBean();
        userBean.setUsername("markus zhang");
        // 正常使用
        userBeanProcessor.process(userBean);
        // 异常调用
        userBean.setUsername(null);
        userBeanProcessor.process(userBean);

        context.close();
    }

    @Component
    @Validated
    static class UserBeanProcessor {
        public void process(@Valid UserBean userBean) {
            System.out.println(userBean);
        }
    }

    static class UserBean {
        @NotBlank  // 在 null 基础上 还校验 "" 空字符串的情况
//        @NotNull // 只判断 null
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "UserBean{" +
                    "username='" + username + '\'' +
                    '}';
        }
    }
}
