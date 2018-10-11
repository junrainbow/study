package jun.liu.redis;

import redis.clients.jedis.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class SentinelPool {
    
    private static final String CONFIG_FILE_PATH = "local-redis-conf4sentinel.properties";
    
    private static final String MASTER_NAME = "mymaster";//10.151.31.142
    
    private static final int PORT = 6379;
    
    private static final int TIMEOUT = 3000;
    
    public static JedisSentinelPool pool;
    
    
    static{
        initPool();
    }
    
    public static Jedis getJedis(){
        Jedis jedis = pool.getResource();
        return jedis;
    }
    
    
    /**
     * 执行方法
     * @param callback
     * @return
     */
    public static <T>T handle(RedisCallback<T> callback){
        Jedis jedis = getJedis();
        return callback.handle(jedis);
    }
    
    
    
    /**
     * 初始化连接池Pool
     */
    private static void initPool(){
        try {
            //Sen
            pool = loadConfig();
        } catch (Exception e) {
            System.out.println("initPool初始化异常！");
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * 加载Config
     * @return
     * @throws IOException
     */
    private static JedisSentinelPool loadConfig() throws IOException{
        //加载配置文件
        Properties p = new Properties();
        InputStream is = SentinelPool.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH);
        p.load(is);
        //获取配置信息
        String sentinelsStr = (String)p.get("sentinels");
        String masterName = (String)p.get("masterName");
        String timeout = (String)p.get("timeout");
        String maxTotal = (String)p.get("maxTotal");
        String maxIdle = (String)p.get("maxIdle");
        String maxWaitMillis = (String)p.get("maxWaitMillis");
        String testOnBorrow = (String)p.get("testOnBorrow");
        String testOnReturn = (String)p.get("testOnReturn");
        String[] sentinelArry = sentinelsStr.split(",");
        Set<String> sentinels = new HashSet<String>(Arrays.asList(sentinelArry));
        //构建config配置对象
        JedisSentinelPool config = new JedisSentinelPool(masterName,sentinels);

        return config;
    }
    
    private static int toInteger(String key) {
        return Integer.parseInt(key.trim());
    }

    private static boolean toBoolean(String key) {
        return Boolean.valueOf(key.trim());
    }
    
    
    /**
     * 根据KEY得VALUE
     * @param key
     * @return
     */
    public static String getValueByKey(final String key){
        RedisCallback<String> callback = new RedisCallback<String>() {
            public String handle(Jedis jedis) {
                String value = jedis.get(key);
                return value;
            }
        };
        String result = SentinelPool.handle(callback);
        System.out.println("执行结果："+result);
        return result;
    }
    
    /**
     * 获得数据类型TYPE
     * @param key
     * @return
     */
    public static String getType(final String key){
        RedisCallback<String> callback = new RedisCallback<String>() {
            public String handle(Jedis jedis) {
                String value = jedis.type(key);
                return value;
            }
        };
        String result = SentinelPool.handle(callback);
        System.out.println("执行结果："+result);
        return result;
    }
    
    
    /**
     * 设置值
     * @param key
     * @return
     */
    public static String setValue(final String key,final String value){
        RedisCallback<String> callback = new RedisCallback<String>() {
            public String handle(Jedis jedis) {
                String result = jedis.set(key, value);
                return result;
            }
        };
        String result = SentinelPool.handle(callback);
        System.out.println("执行结果："+result);
        return result;
    }
    
}
