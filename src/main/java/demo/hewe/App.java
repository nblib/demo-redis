package demo.hewe;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    private static JedisPool jedisPool;

    static {
        try {
            initPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void initPool() throws IOException {
        Properties properties = new Properties();
        JedisPoolConfig jpc = new JedisPoolConfig();

       try {
           properties.load(App.class.getClassLoader().getResourceAsStream("redis.properties"));

           String minIdle = properties.getProperty("redis.MinIdle");
           if (minIdle != null && !minIdle.trim().equals("")){
               jpc.setMinIdle(Integer.parseInt(minIdle));
           }
           String maxIdle = properties.getProperty("redis.MaxIdle");
           if (maxIdle != null && !maxIdle.trim().equals("")){
               jpc.setMaxIdle(Integer.parseInt(maxIdle));
           }
           String blockWhenExhausted = properties.getProperty("redis.blockWhenExhausted");
           if (blockWhenExhausted != null && !blockWhenExhausted.trim().equals("")){
               jpc.setBlockWhenExhausted(Boolean.parseBoolean(blockWhenExhausted));
           }
           String fairness = properties.getProperty("redis.fairness");
           if (fairness != null && !fairness.trim().equals("")){
               jpc.setFairness(Boolean.parseBoolean(fairness));
           }
           String lifo = properties.getProperty("redis.lifo");
           if (lifo != null && !lifo.trim().equals("")){
               jpc.setLifo(Boolean.parseBoolean(lifo));
           }
           String MaxTotal = properties.getProperty("redis.MaxTotal");
           if (MaxTotal != null && !MaxTotal.trim().equals("")){
               jpc.setMaxTotal(Integer.parseInt(MaxTotal));
           }
           String MaxWaitMillis = properties.getProperty("redis.MaxWaitMillis");
           if (MaxWaitMillis != null && !MaxWaitMillis.trim().equals("")){
               jpc.setMaxWaitMillis(Integer.parseInt(MaxWaitMillis));
           }
           String MinEvictableIdleTimeMillis = properties.getProperty("redis.MinEvictableIdleTimeMillis");
           if (MinEvictableIdleTimeMillis != null && !MinEvictableIdleTimeMillis.trim().equals("")){
               jpc.setMinEvictableIdleTimeMillis(Integer.parseInt(MinEvictableIdleTimeMillis));
           }
            String host = properties.getProperty("redis.host");
            String port = properties.getProperty("redis.port");

            if (host == null || "".equals(host.trim())){
                throw new IllegalArgumentException("not set redis host");
            }else {
                if (port != null && !port.trim().equals("")){
                    jedisPool  = new JedisPool(jpc,host,Integer.parseInt(port));
                }else {
                    jedisPool = new JedisPool(jpc,host);
                }
            }

       }catch ( NumberFormatException nex){
           throw new IllegalArgumentException("error argument:",nex);
       }
       if (jedisPool ==null){
           jedisPool = new JedisPool();
       }
    }

    public static void main( String[] args )
    {
        Jedis jedis = jedisPool.getResource();
        String asking = jedis.get("foo");
        System.out.println(asking);


    }
}
