import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.UUID;

public class RedisPoolNotClose {
    public JedisPool jedisPool;

    public RedisPoolNotClose(String host, int port) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.MAX_VALUE);
        this.jedisPool = new JedisPool(config, host, port);
    }

    public void test() {
        long count = 0;
        while (true) {
            String uuid = UUID.randomUUID().toString();
            Jedis jedis = jedisPool.getResource();
            jedis.setex(uuid, 360000, "");
            System.out.println("seted " + uuid);
        }
    }

    public static void main(String[] args) {
        RedisPoolNotClose redisPoolNotClose = new RedisPoolNotClose("127.0.0.1", 6379);
        redisPoolNotClose.test();
    }
}
