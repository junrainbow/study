package jun.liu.redis.test;

import com.alibaba.fastjson.JSON;
import jun.liu.redis.SimplePool;
import jun.liu.redis.test.vo.UserInfoVO;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.sortedset.ZAddParams;

import java.io.IOException;
import java.util.*;

public class BaseTest {

    private static Jedis jedis = SimplePool.getJedis();
//    private static Jedis jedis = SentinelPool.getJedis();
//    private static JedisCluster jedis = ClusterPool.pool;

    public static void main(String[] args) throws ClassNotFoundException, IOException {

        //序列化
        UserInfoVO info = new UserInfoVO();
        info.setLastTime(new Date());
        info.setPwd("123456");
        info.setUsername("junrainbow");
        String junJson = JSON.toJSONString(info);

        UserInfoVO info2 = new UserInfoVO();
        info2.setLastTime(new Date());
        info2.setPwd("123qwe");
        info2.setUsername("yaorainbow");
        String yiJson = JSON.toJSONString(info2);

        //保存redis
        jedis.set("junrainbow",junJson);
        jedis.set("yaorainbow",yiJson);

        //反序列化
        String junrainbow = jedis.get("junrainbow");
        String yaorainbow = jedis.get("yaorainbow");
        info = JSON.parseObject(junrainbow,UserInfoVO.class);
        info2 = JSON.parseObject(yaorainbow,UserInfoVO.class);

        //输出结果
        System.out.println(JSON.toJSONString(info));
        System.out.println(JSON.toJSONString(info2));
        jedis.close();

    }



}
