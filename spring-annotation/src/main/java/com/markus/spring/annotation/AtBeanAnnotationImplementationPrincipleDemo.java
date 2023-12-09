package com.markus.spring.annotation;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;

/**
 * @author: markus
 * @date: 2023/12/9 11:08 PM
 * @Description: @Bean 注解实现原理
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 * @see ConfigurationClassPostProcessor
 * // ConfigurationClassParser#doProcessConfigurationClass
 */
@Configuration
public class AtBeanAnnotationImplementationPrincipleDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AtBeanAnnotationImplementationPrincipleDemo.class);

        context.refresh();

        context.close();
    }

    /**
     * 实现原理:(我们通过 AnnotationConfigApplicationContext 来叙述原理，实际上xml中配置的注解驱动多了一个通过 ContextNamespaceHandler 中转，原理是一样的)
     *      1. 注册注解驱动相关的 Bean 配置（可以看看 AnnotationConfigUtil#registerAnnotationConfigProcessors 方法，我们就直接说 ConfigurationClassPostProcessor 这个Bean）
     *      2. 将 ConfigurationClassPostProcessor 注册到容器中后，在容器上下文启动的时候（具体将IoC容器生命周期时，我们再赘述） ConfigurationClassPostProcessor 会发挥作用。
     *          2.1 它会将容器中涉及的 Configuration class 全部添加进候选配置类中，然后解析这些候选配置类时，会有一个环节处理被 @Bean 标注的工厂方法，将其封装成 BeanDefinitionHolder 返回
     *          2.2 最终将这些 BeanDefinition 注册进 IoC 容器中
     *      3. 具体解析 @Bean 的地方在 ConfigurationClassParser#doProcessConfigurationClass中
     */
    @Bean
    public User user(){
        return User.createUser();
    }
}
