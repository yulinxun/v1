import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu
 * @date 2019/11/7 0007
 */
public class MyTest {
    @Test
    public void testJedis(){
        Jedis jedis=new Jedis("106.13.208.154",6379);
        jedis.auth("yu");
        jedis.incr("zan");
        jedis.decr("zan");
        jedis.incrBy("zan",100L);
        System.out.println(jedis.get("zan"));
    }
    @Test
    public void JedisTest(){
        Jedis jedis=new Jedis("106.13.208.154",6379);
        jedis.auth("yu");
        Map<String,String> map=new HashMap<String, String>();
        map.put("name","yu");
        map.put("age","12");
        jedis.hmset("obj",map);
        Map<String, String> hgetAll = jedis.hgetAll("obj");
        for (String s : hgetAll.keySet()) {
            System.out.println(hgetAll.get(s));
        }
    }
    @Test
    public void watchTest(){
        Jedis jedis = new Jedis("106.13.208.154", 6379);
        jedis.auth("yu");
        Transaction multi = jedis.multi();
        multi.set("money1","30000");
        multi.set("money2","50000");
        multi.exec();

    }
}
