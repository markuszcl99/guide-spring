package com.markus.spring.cache;

import com.markus.spring.cache.util.RedisCacheUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @author: markus
 * @date: 2024/2/10 9:10 AM
 * @Description: RedisCacheUtils 使用示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class RedisCacheUtilsDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-data-redis-context.xml");

        boolean deleted = RedisCacheUtils.deleteKey("apple_watch");
        System.out.println("delete key 'apple_watch' : " + deleted);
        RedisCacheUtils.set("apple_watch", "S8");
        String appleWatch = (String) RedisCacheUtils.get("apple_watch");
        System.out.println(appleWatch);

        long deletedCount = RedisCacheUtils.hBatchDeleteKey("apple_product", "iPhone", "apple_watch");
        System.out.println("delete hKey count : " + deletedCount);

        RedisCacheUtils.hSet("apple_product", "iPhone", "iPhone 13 Pro");
        RedisCacheUtils.hSet("apple_product", "apple_watch", "S8");
        // 获取指定 key 的 Hash 单个 hKey 对应的值
        String iPhone = (String) RedisCacheUtils.hGet("apple_product", "iPhone");
        System.out.println(iPhone);
        // 获取指定 key 的 Hash 所有 hKey 的值
        Map<Object, Object> appleProduct = RedisCacheUtils.hBatchGet("apple_product");
        System.out.println(appleProduct);


    }
}
