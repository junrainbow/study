package jun.liu.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-13 12:45
 */
public class Test {

    public static void main(String[] args) throws Exception {

        //惰性队列
//        lazyQueue();
        //延迟队列
//        delayQueue();
        //阶梯队列
//        progressiveQueue();
        //发送方确认
//        publishConfirm();
        //优先级队列
//        priority();
        //RPC模式
        rpc();

    }

    private static Connection connection = null;
    static{
        try {
            connection  = Connector.getInstance().newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * 惰性队列
     * @throws IOException
     * @throws TimeoutException
     */
    private static void lazyQueue() throws IOException, TimeoutException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("x-queue-mode","lazy");
        Channel channel = connection.createChannel();
        channel.queueDeclare("lazy-queue",true,false,false,map);
        channel.close();
        connection.close();
    }

    /**
     * 延迟队列
     * @throws IOException
     * @throws TimeoutException
     */
    private static void delayQueue() throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare("delay-exchange", "direct", true, false, false, null);
        channel.exchangeDeclare("unlock-order-exchange", "direct", true, false, false, null);
        //声明队列
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("x-message-ttl",60000);
        map.put("x-dead-letter-routing-key","unlock-order");
        map.put("x-dead-letter-exchange","unlock-order-exchange");
        channel.queueDeclare("delay-queue", true, false, false, map);
        channel.queueDeclare("unlock-order-queue", true, false, false, null);
        //绑定关系
        channel.queueBind("delay-queue", "delay-exchange", "delay");
        channel.queueBind("unlock-order-queue", "unlock-order-exchange", "unlock-order");
        channel.close();
        connection.close();
    }

    /**
     * 阶梯队列
     * @throws IOException
     * @throws TimeoutException
     */
    private static void progressiveQueue() throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        //配置参数
        List<Integer> levels = new ArrayList<Integer>();
        levels.add(0);
        levels.add(5);
        levels.add(10);
        levels.add(30);



        //动态生成阶梯队列
        String delayExchange = "";
        String excutorExchange = "";
        String delayQueue = "";
        String excutorQueue = "";
        String nextDelayExchange = "";
        for (int i = 0; i < levels.size(); i++) {

            //是否需要立即执行第一次
            if(levels.get(i)==0){
                nextDelayExchange = "progressive-exchange-"+levels.get(1)+"-delay";
                excutorExchange = "progressive-exchange-"+levels.get(0)+"-excutor";
                channel.exchangeDeclare(excutorExchange, "direct", true, false, false, null);

                excutorQueue = "progressive-queue-"+levels.get(0)+"-excutor";
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("x-dead-letter-exchange",nextDelayExchange);
                channel.queueDeclare(excutorQueue, true, false, false, map);

                channel.queueBind(excutorQueue, excutorExchange, "progressive");
                continue;
            }

            //声明交换机
            delayExchange = "progressive-exchange-"+levels.get(i)+"-delay";
            excutorExchange = "progressive-exchange-"+levels.get(i)+"-excutor";
            channel.exchangeDeclare(delayExchange, "direct", true, false, false, null);
            channel.exchangeDeclare(excutorExchange, "direct", true, false, false, null);

            //声明队列
            delayQueue = "progressive-queue-"+levels.get(i)+"-delay";
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("x-message-ttl",levels.get(i).intValue()*1000);
            map1.put("x-dead-letter-exchange",excutorExchange);
            channel.queueDeclare(delayQueue, true, false, false, map1);

            //最后一次不再追加下一个交换机
            excutorQueue = "progressive-queue-"+levels.get(i)+"-excutor";
            if(levels.size()-1!=i){
                nextDelayExchange = "progressive-exchange-"+levels.get(i+1)+"-delay";
                Map<String,Object> map2 = new HashMap<String,Object>();
                map2.put("x-dead-letter-exchange",nextDelayExchange);
                channel.queueDeclare(excutorQueue, true, false, false, map2);
            }else{
                channel.queueDeclare(excutorQueue, true, false, false, null);
            }


            //绑定关系
            channel.queueBind(delayQueue, delayExchange, "progressive");
            channel.queueBind(excutorQueue, excutorExchange, "progressive");
        }

        //关闭资源
        channel.close();
        connection.close();
    }



    private static void publishConfirm() throws IOException, TimeoutException, InterruptedException {
        //获得通道
        Channel channel = connection.createChannel();
        //开启确认模式
        channel.confirmSelect();
        //构建消息
        byte[] msg = "confirm-msg-1".getBytes();
        //异步确认
        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("handleAck deliveryTag:"+deliveryTag);
            }
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("handleNack deliveryTag:"+deliveryTag);
            }
        });
        //退回确认
        channel.addReturnListener(new ReturnListener() {
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("RETURN-exchange["+exchange+"]，routingKey["+routingKey+"]，replyCode["+replyCode+"],"+new String(body)+"，被退回");
            }
        });
        //发送消息
        channel.basicPublish("confirm-exchange","confirm",false,MessageProperties.PERSISTENT_TEXT_PLAIN ,msg);
        //关闭资源
        channel.close();
        connection.close();
    }


    private static void priority() throws IOException, TimeoutException, InterruptedException {
        //优先级
        int priority = 10;
        //获得通道
        Channel channel = connection.createChannel();
        //构建消息
        byte[] msg = ("priority-msg-"+priority).getBytes();
        //构建消息参数
        AMQP.BasicProperties prop = new AMQP.BasicProperties.Builder().priority(priority).build();
        //发送消息
        channel.basicPublish("priority-exchange","priority",false,prop,msg);
        //关闭资源
        channel.close();
        connection.close();
    }


    private static void rpc() throws InterruptedException, TimeoutException, IOException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    rpcClient();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    rpcServer();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(100000);
    }

    private static void rpcClient() throws IOException, TimeoutException, InterruptedException {
        //消息ID
        String cid = UUID.randomUUID().toString();
        //回调队列
        String reply = "rpc-reply-queue";
        //获得通道
        Channel channel = connection.createChannel();
        //构建消息
        byte[] msg = ("rpc-msg").getBytes();
        //构建消息参数
        AMQP.BasicProperties prop = new AMQP.BasicProperties.Builder().correlationId(cid).replyTo(reply).build();
        //发送消息
        channel.basicPublish("rpc-exchange","rpc",false,prop,msg);
        //处理回调结果
        String callback = callback(channel, cid,reply);
        System.out.println("客户端生产者，收到消息回调的结果：correlationid=["+cid+"]，RPC回调结果["+callback+"]");
        //关闭资源
        channel.close();
        connection.close();
    }

    private static String callback(Channel channel, final String cid,String replyTo) throws IOException, InterruptedException {
        //长度1的阻塞队列
        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);
        //异步获取结果
        channel.basicConsume(replyTo, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if (properties.getCorrelationId().equals(cid)) {
                    response.offer(new String(body, "UTF-8"));
                }
            }
        });
        //无结果时阻塞队列
        return response.take();
    }

    private static void rpcServer() throws IOException, TimeoutException, InterruptedException {
        //获得连接
        final Connection connection = Connector.getInstance().newConnection();
        final Channel channel = connection.createChannel();
        //设置为每次获取1条消息
        channel.basicQos(1);
        //接收消息
        channel.basicConsume("rpc-queue",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //获得消息信息
                String correlationId = properties.getCorrelationId();
                String replyTo = properties.getReplyTo();
                String msg = new String(body,"UTF-8");
                System.out.println("服务端消费者，收到消息并处理：correlationid["+correlationId+"]，replyTo=["+replyTo+"]，接收到消息内容["+ msg+"]");
                //调用biz得到处理结果
                String bizResult = "支付成功";
                //构建回调配置
                AMQP.BasicProperties replyProp = new AMQP.BasicProperties.Builder().correlationId(correlationId).build();
                //将结果发送至reply队列
                channel.basicPublish("",replyTo,replyProp,bizResult.getBytes());
                //确认消费
                channel.basicAck(envelope.getDeliveryTag(),false);
//                channel.basicReject(deliveryTag,false);
//                channel.basicNack(deliveryTag,false,true);
//                channel.basicRecover(false);
            }
        });
    }




}
