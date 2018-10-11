package jun.liu.redis.test;

import jun.liu.redis.SimplePool;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author junrainbow
 * @Description 分布式锁（非阻塞）
 * @Date:Create in 2018-08-01 19:58
 */
public class DistributedLock {

    private static ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<Map<String,String>>(){
        @Override
        protected Map<String,String> initialValue() {
            return new HashMap<String,String>();
        }
    };

    private static Jedis conn = SimplePool.getJedis();


    /**
     * 获取锁
     * @param lockName 锁键值
     * @param expireSec 过期秒
     * @return
     */
    public static boolean tryLock(String lockName,int expireSec){

        String uuid = UUID.randomUUID().toString();

        String result = conn.set(lockName, uuid, "nx", "ex", expireSec);

        if("OK".equals(result)){
            Map<String,String> cache = threadLocal.get();
            cache.put(lockName,uuid);
            return true;
        }
        return false;
    }

    public static boolean tryRelease(String lockName){
        Long succ = 1L;
        Map<String,String> cache = threadLocal.get();
        String uuid = cache.get(lockName);
        if(uuid==null){
            return false;
        }
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = conn.eval(script, Collections.singletonList(lockName), Collections.singletonList(uuid));

        if (succ.equals(result)) {
            return true;
        }
        return false;
    }



    public static void main(String[] args) throws Exception {

    }

}
