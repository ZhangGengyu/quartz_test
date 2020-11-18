package org.zgy.test_redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext-redis.xml")
public class RedisTest {
    public String zsetKey = "test:zset:";
    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redisTemplate;

    @Test
    public void testZSetAdd() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        List list = new ArrayList();
        list.add("barcode8");
        list.add("barcode9");
        zSetOperations.add(zsetKey+"shopId2", "2barcode1", 102l);
//        redisTemplate.expire(zsetKey, 60, TimeUnit.SECONDS);
    }

    @Test
    public void testZSetGet() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        Set set1 = zSetOperations.reverseRange(zsetKey, 0, -1);
        Set<ZSetOperations.TypedTuple> set2 = zSetOperations.rangeWithScores(zsetKey + "shopId1", 0, -1);
        set2.stream().forEach(var -> System.out.println(var.getValue()+"->"+var.getScore()));
    }

    @Test
    public void testExip(){
        int a = 1, b=2, c=3;
        List list = new ArrayList();
        list.add(a);
        System.out.println(list);
    }

    @Test
    public void testPattern() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        List<String> countList = new ArrayList();
        List<String> varList = new ArrayList<>();
        Set keys = redisTemplate.keys("test:zset:*");

        keys.stream().forEach(key -> zSetOperations.range(key, 0, -1).stream().forEach(var -> countList.add((String) var)));

//        keys.stream().forEach(key -> list.add(zSetOperations.range(key, 0, -1).toString()));
        countList.stream().forEach(var -> varList.add(var.substring(1)));
        System.out.println("countList -->> ");
        countList.stream().forEach(var -> System.out.print(var + " "));
        System.out.println();
        System.out.println("varList -->> ");
        varList.stream().forEach(var -> System.out.print(var + " "));
        System.out.println(varList.contains("barcode1"));
    }

    @Test
    public void testGetPattern() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        String key = "abnormal:offline:";

    }
}
