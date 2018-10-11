package jun.liu.redis.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jun.liu.redis.SimplePool;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author junrainbow
 * @Description 延迟队列
 * @Date:Create in 2018-08-02 17:41
 */
public class DelayQuene {

    private static Jedis conn = SimplePool.getJedis();

    /**
     * 入队
     * @return
     */
    public static boolean push(String queneName,long delaymills,Object msg){
        //获取信息
        Double delay = Double.valueOf(System.currentTimeMillis() + delaymills);
        String json = JSON.toJSONString(msg);
        //入队
        Long zadd = conn.zadd(queneName, delay, json);
        if(zadd==1){
            return true;
        }
        return false;
    }


    /**
     * 出队
     * @param queneName
     * @param cls
     * @param <T>
     * @return
     */
    public static <T>T pop(String queneName,Class<T> cls){
        //计算score
        Double currentTime = Double.valueOf(System.currentTimeMillis());
        //出队
        Set<String> element = conn.zrangeByScore(queneName, 0, currentTime);
        if(element==null||element.size()==0){
            return null;
        }
        //反序列化
        String json = (String)element.toArray()[0];
        T t = JSONObject.parseObject(json,cls);
        Long zrem = conn.zrem(queneName, json);
        if(zrem!=1){
            return null;
        }
        return t;
    }



}
