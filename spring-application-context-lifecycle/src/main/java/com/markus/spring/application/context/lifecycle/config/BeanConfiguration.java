package com.markus.spring.application.context.lifecycle.config;

import com.markus.spring.application.context.lifecycle.service.EchoService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;

import java.lang.annotation.Documented;

/**
 * @author: markus
 * @date: 2024/5/15 12:31 AM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
@Import({BeanConfiguration2.class,BeanConfiguration3.class}) // BeanConfiguration3 被多次导入验证 org.springframework.context.annotation.ConfigurationClassParser.processConfigurationClass 的一段逻辑
@PropertySource("classpath:/META-INF/spring.properties")
@EnableAspectJAutoProxy
@ComponentScan({
        "com.markus.spring.application.context.lifecycle.aspect"
})
public class BeanConfiguration implements InitializingBean,IBeanConfiguration {

    @Value("${user.name}")
    private String userName;

    @Value("${user.age}")
    private String age;

    @Bean(name = {"echoAlias1","echoAlias2"})
    public EchoService echoService() {
        return new EchoService();
    }

    @ConditionalOnMissingBean({EchoService.class})
    @Bean
    public EchoService echoService2() {
        return new EchoService();
    }

    @ConditionalOnBean({EchoService.class})
    @Bean
    public EchoService echoService3() {
        return new EchoService();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(userName);
        System.out.println(age);
    }

    @Configuration
    public static class InnerBeanConfiguration {
        @Bean
        public EchoService echoService4() {
            return new EchoService();
        }
    }


    /**
     * 1. 查找候选 Configuration Class，并设置相应的属性值，如 顺序、轻重量级 Configuration
     * 2. 按照顺序排序
     * 3. 查找自定义命名器，如果有的话
     * 4. 构建 ConfigurationClassParser，用来解析每一个 @Configuration Class Bean
     *  4.1 解析
     *      4.1.1 ConditionEvaluator 检查条件是否符合
     *      4.1.2 针对重复导入的 Configuration class 做过滤（场景：A 导入 C，B 导入 C）
     *      4.1.3 递归处理 configuration class 和它的父类
     *          4.1.3.1 处理嵌套配置类，如上面的 InnerBeanConfiguration
     *          4.1.3.2 处理 @PropertySource 注解
     *          4.1.3.3 处理 @ComponentScan 注解，这个环节，如果扫描出来的 Bean 还有候选的 Configuration class，那么就递归调用 parser 方法进行解析
     *          4.1.3.4 处理 @Import 注解
     *          4.1.3.5 处理 @ImportResource 注解
     *          4.1.3.6 处理当前配置类标注的 @Bean 方法（包括继承的超类中的 @Bean 方法）
     *          4.1.3.7 处理超类，如果有的话
     *  4.2 校验
     *      4.2.1 【类修饰符校验】检验 @Configuration Class 修饰符不能是 final 的，除非它被声明为 proxyBeanMethods=false
     *      4.2.2 【方法修饰符校验】校验 @Configuration Class 里面的 @Bean Method 不能是 final、private 的
     *  4.3 加载 BeanDefinition（遍历在解析阶段解析出的所有的 Configuration Class）
     *      4.3.1 判断配置类是否需要跳过，这里运用到 Condition 相关的知识，如果跳过后面步骤就不做处理了（并将该 BeanDefinition 删除掉，包括被 Import 的）
     *      4.3.2 如果当前 Configuration Class 是被动导入的，那么额外处理一步：将自己也包装成 BeanDefinition 进行注册
     *      4.3.3 处理当前类的 BeanMethod，将其 BeanDefinition 注册到容器中（这里有 static 和 非 static @Bean 方法声明区分以及 作用域代理的处理）
     *      4.3.4 处理 ImportResource 引入的配置 BeanDefinition
     *      4.3.5 处理 ImportBeanDefinitionRegistrar 注册的 BeanDefinition
     *  4.4 获取本次注册的 BeanDefinition 中为 @Configuration 的候选
     * 5. 循环执行第 4 步，直到没有了 @Configuration 的候选 Bean
     * 6. 注册 ImportRegistry 作为一个 Bean，为了支持 ImportAware @Configuration classes （获取任意类上标注的注解元信息）
     * 7. 清除 CachingMetadataReaderFactory 共享缓存
     *
     *
     * <p>
     * Scope(proxyMode) 即作用域代理，它的出现就是未了解决一个作用域范围大的 bean 依赖了一个作用域范围小的 bean 的场景
     */
}
