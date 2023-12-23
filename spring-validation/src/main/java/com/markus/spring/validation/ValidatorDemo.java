package com.markus.spring.validation;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.*;

import java.util.Locale;
import java.util.Objects;

/**
 * @author: markus
 * @date: 2023/12/23 10:49 PM
 * @Description: {@link Validator} 自定义校验示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class ValidatorDemo {
    public static void main(String[] args) {
        // 创建 校验器
        Validator validator = new UserValidator();

        // 创建 被校验类
//        User user = User.createUser();
        User user = new User();
        System.out.println("user 是否支持被校验 : " + validator.supports(user.getClass()));

        // 创建 Error 对象
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);

        // 获取 文案
        MessageSource messageSource = createMessageSource();
        for (ObjectError error : errors.getAllErrors()) {
            String message = messageSource.getMessage(Objects.requireNonNull(error.getCode()), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }

    }

    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
            // ... 其他校验
        }
    }

    static MessageSource createMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames("META-INF/validation_text");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
