package com.markus.spring.dependency.injection;

import com.markus.spring.ioc.overview.domain.User;
import com.markus.spring.ioc.overview.domain.UserHolder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/18
 * @Description: @Autowired 注解注入示例
 */
@Configuration
@PropertySource(value = "classpath:/META-INF/application.properties", encoding = "UTF-8")
public class AtAutowiredAnnotationInjectionDemo {

    @Autowired
    private Optional<User> optionalUser;

    @Autowired
    private ObjectFactory<User> userObjectFactory;

    /**
     * @Autowired 注入流程
     * 1. 先按照名称查找
     * 2. 再按照类型查找
     */
    @Autowired
    private User user1;

    @Autowired
    private Collection<User> users;

    @Autowired
    private Map<String, User> userMap;

    @Autowired
    private User[] userArrays;

    private User userFromMethodInjection;

    @Autowired
    public void setUserFromMethodInjection(User user) {
        this.userFromMethodInjection = user;
    }

    @Autowired
    private UserHolder userHolder;

    @Autowired
    private String myName;

    @Value("${my.nickname:小张}")
    private String myNickname;

    @Value("${my_site}")
    private String mySite;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AtAutowiredAnnotationInjectionDemo.class, BeanConfig.class);

        context.refresh();

        AtAutowiredAnnotationInjectionDemo demo = context.getBean(AtAutowiredAnnotationInjectionDemo.class);
        System.out.println("demo.optionalUser : " + demo.optionalUser.get());
        System.out.println("demo.userObjectFactory : " + demo.userObjectFactory.getObject());
        System.out.println("demo.user : " + demo.user1);
        System.out.println("demo.users : " + demo.users);
        System.out.println("demo.userMap : " + demo.userMap);

        System.out.print("demo.userArrays : [ ");
        for (User userArray : demo.userArrays) {
            System.out.print(userArray.getBeanName() + " ");
        }
        System.out.println("]");
        System.out.println("demo.userFromMethodInjection : " + demo.userFromMethodInjection);
        System.out.println("demo.userHolder : " + demo.userHolder);
        System.out.println("demo.myName : " + demo.myName);
        System.out.println("demo.myNickname : " + demo.myNickname);
        System.out.println("demo.mySite : " + demo.mySite);

        context.close();
    }

    @Bean("user1")
    public User user1() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Markus Zhang");
        return user;
    }

    @Bean("user2")
    @Primary
    public User user2() {
        User user = new User();
        user.setId(2L);
        user.setUsername("Luna");
        return user;
    }

    @Bean
    public UserHolder userHolder(@Autowired User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

    @Bean
    public String myName() {
        return "markus zhang";
    }
}
