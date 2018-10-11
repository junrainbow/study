package jun.liu.redis.jun.liu.redis.dev;

import jun.liu.redis.RedisCallback;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class DevRedisTools {
    
    private static final String CONFIG_FILE_PATH = "dev-redis-conf.properties";
    
    private static final String REDIS_URL = "172.21.0.7";//10.151.31.142
    
    private static final int PORT = 6379;
    
    private static final int TIMEOUT = 3000;
    
    public static JedisPool pool;
    
    
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
            //加载config
            JedisPoolConfig config = loadConfig();
            //构造连接池
            pool = new JedisPool(config, REDIS_URL, PORT, TIMEOUT);
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
    private static JedisPoolConfig loadConfig() throws IOException{
        //加载配置文件
        Properties p = new Properties();
        InputStream is = DevRedisTools.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH);
        p.load(is);
        //获取配置信息
        String url = (String)p.get("sentinels");
        String masterName = (String)p.get("masterName");
        String maxTotal = (String)p.get("maxTotal");
        String maxIdle = (String)p.get("maxIdle");
        String maxWaitMillis = (String)p.get("maxWaitMillis");
        String testOnBorrow = (String)p.get("testOnBorrow");
        String testOnReturn = (String)p.get("testOnReturn");
        String timeout = (String)p.get("timeout");
//        System.out.println(url);
//        System.out.println(masterName);
//        System.out.println(maxTotal);
//        System.out.println(maxIdle);
//        System.out.println(maxWaitMillis);
//        System.out.println(testOnBorrow);
//        System.out.println(testOnReturn);
//        System.out.println(timeout);
        //构建config配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(toInteger(maxTotal));
        config.setMaxIdle(toInteger(maxIdle));
        config.setMaxWaitMillis(toInteger(maxWaitMillis));
        config.setTestOnBorrow(toBoolean(testOnBorrow));
        config.setTestOnReturn(toBoolean(testOnReturn));
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
        String result = DevRedisTools.handle(callback);
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
        String result = DevRedisTools.handle(callback);
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
        String result = DevRedisTools.handle(callback);
        System.out.println("执行结果："+result);
        return result;
    }
    
}
