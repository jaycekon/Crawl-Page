package com.Jayce.Redis.Utils;

import com.Jayce.Redis.Model.User;
import com.sun.org.apache.xpath.internal.SourceTree;
import redis.clients.jedis.Jedis;
import redis.clients.util.SafeEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/1.
 */
public class JedisClient {
    public static void main(String[] args) {
//        String host = "118.89.50.53";
//        int port = 6381;
//        Jedis jedis = new Jedis(host, port);
//        setString(jedis);
//        setObject(jedis);
//        jedis.close();
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
