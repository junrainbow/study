package jun.liu.redis.test;

import jun.liu.redis.SimplePool;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author junrainbow
 * @Description 限流器
 * @Date:Create in 2018-08-03 14:24
 */
public class RateLimiter {

    private static Jedis conn = SimplePool.getJedis();

    /**
     * 限流
     * @param key
     * @param limitSec
     * @param limitCount
     * @return
     */
    public static boolean limit(String key,int limitSec,int limitCount) throws IOException {
        //构建参数
        long period = limitSec * 1000;
        long end = System.currentTimeMillis();
        long begin = end - period;
        String value = UUID.randomUUID().toString();
        List<String> list = new ArrayList<String>();
        list.add(begin+"");
        list.add(limitCount+"");
        list.add(end+"");
        list.add(value+"");

        String script =
                "redis.call('zremrangeByScore',KEYS[1],0,ARGV[1]) " +
                "local count = redis.call('zcard', KEYS[1]) "+
                "if count < tonumber(ARGV[2]) " +
                "    then redis.call('zadd',KEYS[1],tonumber(ARGV[3]),ARGV[4]) return 1 " +
                "    else return 0 " +
                "end";
        Long eval = (Long) conn.eval(script, Collections.singletonList(key), list);
        return 1==eval?true:false;

//        //准备管道
//        Pipeline line = conn.pipelined();
//        line.multi();
//
//        //加入跳表
//        line.zadd(key,end,value);
//
//        //删除陈旧数据
//        line.zremrangeByScore(key, 0, begin);
//
//        //获取最新数据
//        Response<Long> future = line.zcard(key);
//
//        //设置过期时间
//        line.expire(key,limitSec+1);
//
//        //提交事务
//        line.exec();
//
//        //关闭管道
//        line.close();
//
//        //异步获取count
//        Long count = future.get();

        //是否超限
    }
}
