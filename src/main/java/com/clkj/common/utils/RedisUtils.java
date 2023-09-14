package com.clkj.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    public void set(String key, Object value) {
        valueOperations.set(key, toJson(value));
    }

    public <T> T get(String key, Class<T> clazz) {
        String value = get(key);
        return value == null ? null : fromJson(value, clazz);
    }

    public String get(String key) {
        return valueOperations.get(key);
    }

    public void setBytesEx(byte[] key, byte[] value, int seconds) {
        redisTemplate.execute(connection -> {
            connection.setEx(key, seconds, value);
            return null;
        }, false);
    }

    public byte[] getBytes(byte[] key) {
        Object execute = redisTemplate.execute((RedisCallback<byte[]>) connection -> connection.get(key));
        return (byte[]) execute;
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public Long incr(String key) {
        return valueOperations.increment(key);
    }

    public Long decr(String key) {
        return valueOperations.decrement(key);
    }

    /**
     * 只修改value，不修改剩余时间，新对象长度不能小于已保存对象的长度
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key    缓存的键值
     * @param value  缓存的值
     * @param offset 偏移量
     */
    public void update(final String key, final String value, long offset) {
        valueOperations.set(key, value, offset);
    }

    /**
     * 利用setnx原子性返回加锁是否成功
     *
     * @param lockKey   lockKey
     * @param lockValue lockValue
     * @param timeout   过期时间
     * @param unit      unit
     * @return boolean
     */
    public boolean lock(String lockKey, String lockValue, long timeout, TimeUnit unit) {
        Boolean bool = valueOperations.setIfAbsent(lockKey, lockValue, timeout, unit);
        return bool != null && bool;
    }

    /**
     * set并设置过期时间
     *
     * @param key    key
     * @param value  value
     * @param expire expire
     * @param unit   unit
     */
    public void expire(String key, Object value, long expire, TimeUnit unit) {
        valueOperations.set(key, toJson(value), expire, unit);
    }

    public Long getExpiry(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return JSONObject.toJSONString(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, clazz);
    }

}
