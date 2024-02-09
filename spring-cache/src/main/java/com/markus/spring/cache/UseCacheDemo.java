package com.markus.spring.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/2/1
 * @Description: 使用缓存示例
 */
public class UseCacheDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-data-redis-context.xml");

        RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("my_name", "markuszhang");
        String myName = (String) redisTemplate.opsForValue().get("my_name");
        System.out.println(myName);
    }

}
