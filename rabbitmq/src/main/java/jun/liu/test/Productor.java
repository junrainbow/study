package jun.liu.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.rabbitmq.client.*;
import jun.liu.test.test.qa.AES2;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-13 12:50
 */
public class Productor implements Runnable{

    //交换机配置
    private static final String EXCHANGE_NAME = "exchange1";
    private static final String EXCHANGE_TYPE = "direct";
    private static final boolean EXCHANGE_DURABLE = true;
    private static final boolean EXCHANGE_AUTO_DEL = false;
    private static final boolean EXCHANGE_INTERNAL = false;
    private static final Map<String, Object> EXCHANGE_PARAMS = new HashMap<String, Object>();

    //队列配置
    private static final String QUEUE_NAME = "queue1";
    private static final boolean QUEUE_DURABLE = true;
    private static final boolean QUEUE_EXCLUSIVE = false;
    private static final boolean QUEUE_AUTODELETE = false;
    private static final Map<String, Object> QUEUE_PARAMS = new HashMap<String, Object>();

    //关系配置
    private static final String BIND_KEY = "ORDER.PAY.SUCCESS";
    private static final String ROUTER_KEY = "ORDER.PAY.SUCCESS";

    //多线程模拟
    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(1000,1000,10, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
    private static final int loop = 50000;
    private static final AtomicInteger ai = new AtomicInteger(1);
    private static final CyclicBarrier cb = new CyclicBarrier(1000);
    private static final CountDownLatch cdl = new CountDownLatch(loop);
    private static Connection connection = null;
    private static final AtomicInteger counter = new AtomicInteger(0);


    /**
     * 测试MQ
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //创建交换机
//        Productor.createExchange("lazy-exchange",true,false,null);
        //创建队列
//        Productor.createQueue(QUEUE_NAME,true);
        //绑定
//        Productor.bind("lazy-queue","lazy-exchange","lazy");
        //发布消息
//        MqVO msg = new MqVO("merchant001","1");
//        Productor.send(msg,"progressive-exchange-0-excutor","progressive");
        batchSend();
    }





    private static void batchSend() throws InterruptedException, IOException, TimeoutException {
        //拿连接
        connection = Connector.getInstance().newConnection();
        //批量发送
        long begin = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            Productor t = new Productor();
            pool.submit(t);
        }
        //等待关闭资源
        cdl.await();
        long end = System.currentTimeMillis();
        System.out.println("本次publish["+loop+"]条消息，耗时"+(end-begin)+"ms");
        try {
            connection.close();
        }catch (Throwable t){
            System.out.println("连接关闭异常");
        }
        pool.shutdown();
        System.out.println("进程退出……");

    }
    public void run() {
        try {
            MqVO msg = new MqVO("delay-test",ai.getAndIncrement()+"");
            cb.await();
            Productor.onlySend(msg,"mirror-exchange","mirror");
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            cdl.countDown();
        }
    }







    public static void createExchange(String exchangeName,boolean durable,boolean internal,Map<String,Object> params) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = Connector.getInstance();
            connection = factory.newConnection();
            channel = connection.createChannel();

            //声明交换机
            AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare(exchangeName, EXCHANGE_TYPE, durable, EXCHANGE_AUTO_DEL, internal, params);
        } catch (Throwable t) {
            System.out.println(t);
        } finally {
            channel.close();
            connection.close();
        }
    }


    public static void createQueue(String queueName,boolean durable) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = Connector.getInstance();
            connection = factory.newConnection();
            channel = connection.createChannel();

            //声明队列
            AMQP.Queue.DeclareOk declareOk1 = channel.queueDeclare(queueName, durable, QUEUE_EXCLUSIVE, QUEUE_AUTODELETE, QUEUE_PARAMS);
        } catch (Throwable t) {
            System.out.println(t);
        } finally {
            channel.close();
            connection.close();
        }
    }

    public static void bind(String queueName, String exchangeName, String bindKey) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = Connector.getInstance();
            connection = factory.newConnection();
            channel = connection.createChannel();

            //绑定关系
            channel.queueBind(queueName, exchangeName, bindKey);
        } catch (Throwable t) {
            System.out.println(t);
        } finally {
            channel.close();
            connection.close();
        }
    }

    public static void bindOtherExchange(String srcExchangeName, String distExchangeName, String bindKey) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = Connector.getInstance();
            connection = factory.newConnection();
            channel = connection.createChannel();

            //绑定关系
            channel.exchangeBind(distExchangeName,srcExchangeName,bindKey);
        } catch (Throwable t) {
            System.out.println(t);
        } finally {
            channel.close();
            connection.close();
        }
    }


    public static void send(MqVO msgVO,String exchangeName,String routerKey) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = Connector.getInstance();
            connection = factory.newConnection();
            channel = connection.createChannel();

            //数据
            byte[] msg = JSON.toJSONString(msgVO).getBytes("UTF-8");
            //发送
            Map<String,Object> header = new HashMap<String,Object>();
            header.put("name","jun");
            AMQP.BasicProperties.Builder prop = new AMQP.BasicProperties.Builder();
//            prop.expiration("20000");
            prop.deliveryMode(2);
            prop.headers(header);
            channel.basicPublish(exchangeName, routerKey, prop.build(), msg);
//            prop.expiration("2000");
//            prop.deliveryMode(2);
//            channel.basicPublish(exchangeName, routerKey, prop.build(), msg);
        } catch (Throwable t) {
            System.out.println(t);
        } finally {
            channel.close();
            connection.close();
        }
    }


    public static void onlySend(MqVO msgVO,String exchangeName,String routerKey) throws IOException, TimeoutException {
        //数据
        byte[] msg = JSON.toJSONString(msgVO).getBytes("UTF-8");
        //发送
        Channel channel = connection.createChannel();
        try {
            channel.basicPublish(exchangeName, routerKey,true, MessageProperties.PERSISTENT_TEXT_PLAIN, msg);
        }catch (Throwable t){
            System.out.println("开启mandatory");
            t.printStackTrace();
        }
        channel.close();
    }



}
