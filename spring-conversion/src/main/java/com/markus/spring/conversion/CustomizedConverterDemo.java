package com.markus.spring.conversion;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

/**
 * @author: markus
 * @date: 2023/12/30 8:47 PM
 * @Description: 使用 自定义 {@link ConditionalGenericConverter} 来 实现 Properties to String 的功能，并通过 {@link ConversionService} 进行类型转换服务
 * @Blog: https://markuszhang.com
 * @see ConditionalGenericConverter
 * @see ConversionService
 * It's my honor to share what I've learned with you!
 */
public class CustomizedConverterDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/property-editor-context.xml");


        User user = context.getBean(User.class);
        System.out.println(user);

        System.out.println("=============ConversionService's Converters============");
        ConversionService conversionService = context.getBean(ConversionService.class);
        System.out.println(conversionService);

        context.close();
    }
}
