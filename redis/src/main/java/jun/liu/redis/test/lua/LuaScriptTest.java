package jun.liu.redis.test.lua;

import jun.liu.redis.SimplePool;
import redis.clients.jedis.Jedis;

public class LuaScriptTest {
    
    private static int 限制时间 = 10;//秒
    private static int 允许次数 = 1;//次
    
    
    private static final String LIMIT_SCRIPT = 
            " local times = redis.call('incr', KEYS[1]) "+
            " if times == 1 then "+
            " redis.call('expire', KEYS[1], ARGV[1]) "+
            " end "+
            " if times > tonumber(ARGV[2]) then "+
            " return 0 "+
            " end "+
            " return 1 ";
    
    public static void main(String[] args) {
        //获得连接
        Jedis jedis = SimplePool.getJedis();
        //获得脚本SHA1短名称
        String scriptSHA1 = jedis.scriptLoad(LIMIT_SCRIPT);
        System.out.println("脚本名称："+scriptSHA1);
        //执行Lua脚本
        Long needLimit = (Long)jedis.evalsha(scriptSHA1, 1, "trade-merchant001-refund",限制时间+"",允许次数+"");
        if(needLimit==1){
            System.out.println("未超过频次限制，可以调用退款业务……");
        }else{
            System.out.println("您在["+限制时间+"]秒内，只能请求["+允许次数+"]次");
        }
    }

}
