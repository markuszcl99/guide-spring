package com.markus.spring.configuration.metadata;

import com.markus.spring.configuration.component.UserComponent;
import com.markus.spring.configuration.config.BeanConfig;
import com.markus.spring.ioc.overview.domain.SuperUser;
import com.markus.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/13
 * @Description: 利用注解 装载 IoC 容器信息
 */
@Configuration
@Import(BeanConfig.class)
@ImportResource("classpath:/META-INF/annotated-as-ioc-beans.xml") // 代替 xml中的 <import />
@ComponentScans({
    @ComponentScan(basePackages = "com.markus.spring.configuration.component")
})
@PropertySources({
    // 不指定编码格式，框架会直接读取字节流，字节流在处理输入输出时，只会简单的读写字节
    // 而指定了字符编码格式后，在处理输入输出时，是按照字符为单位进行读取，所以在一些情况下很有可能会出现乱码，比如出现中文
    @PropertySource(value = "classpath:/META-INF/user.properties", encoding = "UTF-8")
})
public class AnnotatedSpringAsIoCContainerDemo {

  /**
   * 字符编码 知识点
   * 一个字符占用的字节数取决于具体的字符编码方式。以下是一些常见的字符编码方式及其对应的字节数：
   * <p>
   * ASCII：每个字符占用 1 个字节。ASCII 只能表示 128 个不同的字符，包括英文大小写字母、数字和一些特殊字符。
   * <p>
   * ISO-8859-1：每个字符占用 1 个字节。ISO-8859-1 可以表示 256 个不同的字符，包括西欧语言的字符。
   * <p>
   * GBK：每个字符占用 1~2 个字节。GBK 是一种用于表示简体中文字符的编码方式，它可以表示 21003 个不同的字符。
   * <p>
   * UTF-8：每个字符占用 1~4 个字节。UTF-8 是一种用于表示 Unicode 字符的编码方式，它可以表示所有的 Unicode 字符。
   * <p>
   * UTF-16：每个字符占用 2 或 4 个字节。UTF-16 也是一种用于表示 Unicode 字符的编码方式。
   * <p>
   * UTF-32：每个字符占用 4 个字节。UTF-32 也是一种用于表示 Unicode 字符的编码方式。
   * <p>
   * 注意，以上的字节数是指一个字符在内存中或在文件中的存储大小，而不是在显示时的宽度。例如，一个中文字符在显示时通常占用两个英文字符的宽度，但在 UTF-8 编码下，它可能占用 3 个字节的存储空间。
   */
  @Bean("user-by-property")
  public User user(@Value("${user.id}") Long userId,
                   @Value("${user.name}") String userName,
                   @Value("${user.address}") String address) {
    SuperUser user = new SuperUser();
    user.setId(userId);
    user.setUsername(userName);
    user.setAddress(address);
    return user;
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(AnnotatedSpringAsIoCContainerDemo.class);
    context.refresh();

    UserComponent userComponent = context.getBean(UserComponent.class);
    System.out.println(userComponent);

    Map<String, User> beansOfType = context.getBeansOfType(User.class);
    beansOfType.forEach((beanName, bean) -> {
      System.out.println("Bean name: " + beanName + ", Bean instance: " + bean);
    });

    context.close();
  }
}
