package demo.hewe;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JedisPoolConfig jpc = new JedisPoolConfig();
        //当线程池中没有多余的连接时,是否等待
        jpc.setBlockWhenExhausted(true);
        jpc.setFairness(true);
        jpc.setLifo(true);
        jpc.setMaxIdle(10);
        jpc.setMaxTotal(100);
        jpc.setMaxWaitMillis(1000);
        jpc.setMinEvictableIdleTimeMillis(100);
        jpc.setMinIdle(2);


    }
}
