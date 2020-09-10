package zgy.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisDemo {

    @Autowired
    RedisTemplate redisTemplate;

    public void test(){
        System.out.println("commit push 之后加的");
    }
}
