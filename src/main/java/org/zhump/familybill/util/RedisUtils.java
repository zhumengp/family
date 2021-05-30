package org.zhump.familybill.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * TODO redis工具类
 *
 * @author zhump
 * @date 2021/5/30 17:42
 **/

@Component
public class RedisUtils {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     *以字符串方式存储
     * @param key
     * @param value
     */
    public void set(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }

    /**
     * 获取字符串
     * @param key
     * @return
     */
    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * hash值存储
     * @param key
     * @param arg2
     * @param arg3
     */
    public void hashSet(String key,String arg2,String arg3){
        stringRedisTemplate.opsForHash().put(key,arg2,arg3);
    }

    /**
     * 获取HASH值
     * @param key
     * @param value
     * @return
     */
    public Object hashGet(String key,String value){
        return stringRedisTemplate.opsForHash().get(key, value);
    }



}
