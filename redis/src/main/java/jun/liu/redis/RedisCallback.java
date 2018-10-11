package jun.liu.redis;

import redis.clients.jedis.Jedis;

public interface RedisCallback<T> {
    
    /**
     * 模板方法
     * @param jedis
     * @return
     */
    public T handle(Jedis jedis);

}
