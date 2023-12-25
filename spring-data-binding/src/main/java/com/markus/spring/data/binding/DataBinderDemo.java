package com.markus.spring.data.binding;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/25
 * @Description: {@link DataBinder}
 */
public class DataBinderDemo {
  public static void main(String[] args) {
    User user = new User();
    DataBinder dataBinder = new DataBinder(user);

    Map<String, String> source = new HashMap<>();
    source.put("id", "1");
    source.put("username", "markus zhang");

    // 1. PropertyValues 中包含 User 中没有的配置，看下 数据绑定期间有什么问题
    source.put("myName", "zhangcl");

    // 2. PropertyValues 中包含嵌套类型
    source.put("company.companyName", "MT");

    // 3. PropertyValues 包含枚举类型
    source.put("city", "HEZE");

    MutablePropertyValues propertyValues = new MutablePropertyValues(source);

    dataBinder.bind(propertyValues);

    System.out.println(user);
  }
}
