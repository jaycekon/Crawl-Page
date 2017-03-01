package com.Jayce.Redis.Test;

import com.Jayce.Redis.Model.User;
import com.Jayce.Redis.Utils.SerializeUtil;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

/**
 * Created by Administrator on 2017/3/1.
 */
public class JedisPoolClient {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-Redis.xml");
        Pool<Jedis> jedisPool = (Pool)applicationContext.getBean("redisClient");
        Jedis jedis = jedisPool.getResource();
        setString(jedis);
        setObject(jedis);
        jedisPool.returnResource(jedis);
    }

    private static void setString(Jedis jedis) {
        jedis.set("name", "jayce");
        String name = jedis.get("name");
        System.out.println(name);
    }


    private static void setObject(Jedis jedis) {
        User user = new User();
        user.setId(1);
        user.setName("jayce");
        user.setPassword("kong");
        byte[] values = SerializeUtil.serialize(user);
        byte[] names = "user".getBytes();
        jedis.set(names, values);
        byte[] bytes = jedis.get("user".getBytes());
        User userCache = (User) SerializeUtil.unserialize(bytes);
        System.out.println(userCache);

    }
}
