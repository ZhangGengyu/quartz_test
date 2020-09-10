package zgy.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext-redis.xml")
public class RedisTest {
    public static String zsetKey = "test:zset:";
    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redisTemplate;

    @Test
    public void testZSetAdd() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add(zsetKey + "shopId2", "barcode5", 98l);
    }

    @Test
    public void testZSetGet() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        Set set1 = zSetOperations.reverseRange(zsetKey + "shopId1", 0, -1);
        Set<ZSetOperations.TypedTuple> set2 = zSetOperations.rangeWithScores(zsetKey + "shopId1", 0, -1);
        set2.stream().forEach(var -> System.out.println(var.getValue()+"->"+var.getScore()));
    }

}
