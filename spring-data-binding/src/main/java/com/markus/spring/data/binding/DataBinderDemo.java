package com.markus.spring.data.binding;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/25
 * @Description: {@link DataBinder}
 * @see BeanWrapper
 * @see BeanPropertyBindingResult
 */
public class DataBinderDemo {
  public static void main(String[] args) {
    User user = new User();
    DataBinder dataBinder = new DataBinder(user);

    Map<String, Object> source = new HashMap<>();
    source.put("id", "1");
//    source.put("username", "markus zhang");

    // 1. PropertyValues 中包含 User 中没有的配置，看下 数据绑定期间有什么问题
    source.put("myName", "zhangcl");

    // 2. PropertyValues 中包含嵌套类型
//    Company company = new Company();
//    company.setCompanyName("MT");
//    source.put("company", company);
    source.put("company.companyName", "MT");

    // 3. PropertyValues 包含枚举类型
    source.put("city", "HEZE");

    // 忽视位置字段 source 中含有的，但是 User 没有
//    dataBinder.setIgnoreUnknownFields(false);

    // 调整 自动生成嵌套属性
//    dataBinder.setAutoGrowNestedPaths(false);
//    dataBinder.setIgnoreInvalidFields(true);

    // 设置允许绑定的字段，包括"id"、"username"和"city"字段。
//    dataBinder.setAllowedFields("id", "username", "city");
    // 设置不允许绑定的字段
//    dataBinder.setDisallowedFields("company");
    // 设置必须要绑定的字段，这里不会报错，会将绑定结果记录到BindingResult中
    dataBinder.setRequiredFields("id", "username");


    MutablePropertyValues propertyValues = new MutablePropertyValues(source);
    dataBinder.bind(propertyValues);

    System.out.println(user);
    BindingResult bindingResult = dataBinder.getBindingResult();
    System.out.println(bindingResult);
    MessageSource messageSource = createMessageSource();
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      String code = "required." + fieldError.getField();
      String message = messageSource.getMessage(code, fieldError.getArguments(), fieldError.getDefaultMessage(), Locale.getDefault());
      System.out.println(message);
    }
  }


  static MessageSource createMessageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.addBasenames("META-INF/error");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }
}
