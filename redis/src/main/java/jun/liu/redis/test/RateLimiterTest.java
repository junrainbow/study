package jun.liu.redis.test;

import java.util.Date;

/**
 * @author junrainbow
 * @Description 限流器
 * @Date:Create in 2018-08-03 14:24
 */
public class RateLimiterTest {

    public static void main(String[] args) throws Exception {
        int idx = 0;
        while (true){
            for (int i = 0; i < 60; i++) {
                idx++;
                boolean limit = RateLimiter.limit("order:pay:1105555", 60, 60);
                if(limit){
                    System.out.println("当前时间："+new Date()+"，总执行次数："+idx+"，成功次数："+(i+1));
                }else{
                    System.out.println("总执行次数："+idx);
                }
                Thread.sleep(300);
            }
        }
    }
}
