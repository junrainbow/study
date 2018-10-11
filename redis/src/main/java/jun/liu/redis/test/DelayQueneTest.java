package jun.liu.redis.test;

import jun.liu.redis.test.vo.DelayQueneMsgVO;

import java.util.Date;
import java.util.Random;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-02 17:41
 */
public class DelayQueneTest {

    private static final int loop = 100;

    private static final String queneName = "delay:quene";

    public static void main(String[] args) throws Exception {
        //批量入队
        for (int i = 0; i < loop; i++) {
            Random r = new Random();
            int offset = r.nextInt(20000);

            DelayQueneMsgVO msg = new DelayQueneMsgVO();
            msg.setCurrentTime(new Date());
            msg.setDelayMills(offset);
            DelayQuene.push(queneName,offset,msg);
        }

        //延迟出队
        while (true){
            DelayQueneMsgVO delayMsg = DelayQuene.pop(queneName, DelayQueneMsgVO.class);
            if(delayMsg!=null){
                System.out.println("出队成功，入队时间："+delayMsg.getCurrentTime().getTime()+"，延迟ms："+delayMsg.getDelayMills()+"，出队时间："+System.currentTimeMillis());
            }
            Thread.sleep(100);
        }
    }

}
