package kuangredis;

import com.example.demo.SpringBootWildApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWildApplication.class)
public class KuangRedisTest {


    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void contextLoads() {
        // 可以传一个对象json
        redisTemplate.opsForValue().set("myKey","北京市昌平区");
        System.out.println("获取的值是："+redisTemplate.opsForValue().get("myKey"));

//        redisTemplate.opsForHyperLogLog().add("key","");
    }
}
