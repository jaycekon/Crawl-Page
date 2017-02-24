package com.Jayce.Service;

import com.Jayce.Dao.UserDao;
import com.Jayce.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/2/24.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void setUserToRedis(User user){
        userDao.add(user);
    }

    public User getUserFromRedis(int id){
        return userDao.get("1");
    }

    public static void main(String[] args){
        String ip = "118.89.50.53";
        Jedis jedis = new Jedis(ip,6380);
        jedis.set( "hello", "world" ) ;
        String name = jedis.get("name");
        System.out.println(name);
    }

}
