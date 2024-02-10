package com.markus.spring.cache.util;

import com.markus.spring.ioc.overview.util.SpringContextUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: markus
 * @date: 2024/2/9 1:19 PM
 * @Description: redis 客户端操作 工具类封装
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public abstract class RedisCacheUtils {

    private static final TimeUnit defaultTimeUnit = TimeUnit.MINUTES;

    @SuppressWarnings("unchecked")
    private static RedisTemplate<String, Object> redisTemplate = SpringContextUtil.getBean("redisTemplate", RedisTemplate.class);

    /**
     * 设置过期时间 默认时间单位为 秒
     *
     * @param key
     * @param timeout
     * @return
     */
    public static boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置过期时间 可自定义时间单位
     *
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    public static boolean expire(final String key, final long timeout, final TimeUnit timeUnit) {
        Boolean result = redisTemplate.expire(key, timeout, timeUnit);
        return result != null && result;
    }

    /**
     * 删除 指定主键
     *
     * @param key
     * @return
     */
    public static boolean deleteKey(final String key) {
        Boolean delete = redisTemplate.delete(key);
        return delete != null && delete;
    }

    /**
     * 删除 主键集合
     *
     * @param keys
     * @return 删除的主键的个数
     */
    public static long deleteKeys(final Collection<String> keys) {
        Long delete = redisTemplate.delete(keys);
        return delete != null ? delete : 0;
    }

    /**
     * 存储普通对象
     *
     * @param key
     * @param value
     */
    public static void set(final String key, final Object value) {
        redisTemplate.opsForValue().set(key, value, 1, defaultTimeUnit);
    }

    /**
     * 存储普通对象 自定义过期时间
     *
     * @param key
     * @param value
     * @param expireTime
     */
    public static void setWithExpireTime(final String key, final Object value, final Long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, defaultTimeUnit);
    }

    /**
     * 获取普通对象
     *
     * @param key
     * @return
     */
    public static Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // hash 数据结构的操作

    /**
     * hash key 中是否存在 hKey 数据
     *
     * @param key
     * @param hKey
     * @return
     */
    public static boolean hasHashKey(final String key, final String hKey) {
        return redisTemplate.opsForHash().hasKey(key, hKey);
    }

    /**
     * hash 数据结构 设置值
     *
     * @param key
     * @param hKey
     * @param value
     */
    public static void hSet(final String key, final String hKey, final Object value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * hash 数据结构 批量设置值
     *
     * @param key
     * @param values
     */
    public static void hBatchSet(final String key, final Map<String, Object> values) {
        redisTemplate.opsForHash().putAll(key, values);
    }

    /**
     * hash 数据结构 获取值
     *
     * @param key
     * @param hKey
     * @return
     */
    public static Object hGet(final String key, final String hKey) {
        return redisTemplate.opsForHash().get(key, hKey);
    }

    /**
     * hash 数据结构 获取指定的字段数据
     *
     * @param key
     * @return
     */
    public static Map<Object, Object> hBatchGet(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * hash 数据结构 批量获取指定值
     *
     * @param key
     * @param hKeys
     * @return
     */
    public static List<Object> hMultiGet(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * hash 数据结构 批量删除指定的key
     *
     * @param key
     * @param hKeys
     * @return
     */
    public static long hBatchDeleteKey(final String key, final Object... hKeys) {
        return redisTemplate.opsForHash().delete(key, hKeys);
    }

    // set 数据结构的操作

    /**
     * 往 set 数据结构中添加数据
     *
     * @param key
     * @param value
     * @return
     */
    public static long sAdd(final String key, final Object... value) {
        Long addedCount = redisTemplate.opsForSet().add(key, value);
        return addedCount != null ? addedCount : 0;
    }

    /**
     * 批量删除 set 数据结构中指定的 value
     *
     * @param key
     * @param values
     * @return
     */
    public static long sDelete(final String key, final Object... values) {
        Long removed = redisTemplate.opsForSet().remove(key, values);
        return removed == null ? 0 : removed;
    }

    /**
     * 获取 指定key 的 set 数据
     *
     * @param key
     * @return
     */
    public static Set<Object> sGetAll(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 往 zset 中添加数据
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    public static boolean zSetAdd(final String key, final Object value, final double score) {
        Boolean added = redisTemplate.opsForZSet().add(key, value, score);
        return added != null && added;
    }

    /**
     * 删除 zset 中指定的数据
     *
     * @param key
     * @param values
     * @return
     */
    public static long zSetDelete(final String key, final Object... values) {
        Long removed = redisTemplate.opsForZSet().remove(key, values);
        return removed != null ? removed : 0;
    }

    // list 数据结构相关操作

    /**
     * 往 list 中添加数据
     *
     * @param key
     * @param value
     * @return
     */
    public static long listAdd(final String key, final Object value) {
        Long pushed = redisTemplate.opsForList().rightPush(key, value);
        return pushed != null ? pushed : 0;
    }

    /**
     * 往 list 中添加多条数据
     *
     * @param key
     * @param values
     * @return
     */
    public static long listAddAll(final String key, final Object... values) {
        Long pushed = redisTemplate.opsForList().rightPushAll(key, values);
        return pushed != null ? pushed : 0;
    }

    /**
     * 获取 list 的区间集合数据
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static List<Object> listGet(final String key, final int start, final int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

}
