import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yu
 * @date 2019/11/8 0008
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")
public class Mytest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void stringTest(){
        redisTemplate.opsForValue().set("target","nba");
        Object target = redisTemplate.opsForValue().get("target");
        System.out.println(target);
    }
}
