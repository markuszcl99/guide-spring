package com.markus.spring.bean.scope;

import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.markus.spring.bean.scope.ThreadLocalScope.THREAD_LOCAL_SCOPE_NAME;

/**
 * @author: markus
 * @date: 2023/12/17 7:13 PM
 * @Description: 自定义 Scope 实现 {@link ThreadLocalScope}
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(scopeName = THREAD_LOCAL_SCOPE_NAME)
    public User user() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ThreadLocalScopeDemo.class);

        context.addBeanFactoryPostProcessor(new BeanFactoryPostProcessor() {
            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                beanFactory.registerScope(THREAD_LOCAL_SCOPE_NAME, new ThreadLocalScope());
            }
        });

        // 启动 Spring 应用上下文
        context.refresh();

        scopeLookup(context);

        // 关闭 Spring 应用上下文
        context.close();
    }

    private static void scopeLookup(BeanFactory beanFactory) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                User first = beanFactory.getBean("user", User.class);
                User second = beanFactory.getBean("user", User.class);
                System.out.printf("Thread id %d, User is %s%n", Thread.currentThread().getId(), first);
                System.out.printf("Thread id %d, User is %s%n", Thread.currentThread().getId(), second);
            });

            thread.start();
            thread.join();
        }
    }
}
