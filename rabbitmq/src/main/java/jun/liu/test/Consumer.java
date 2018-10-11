package jun.liu.test;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-13 15:59
 */
public class Consumer {



    //消费配置
    private static final String QUEUE_NAME = "priority-queue";
    private static final boolean AUTO_ACK = false;


    public static void receive() throws IOException, TimeoutException {
        //获得连接
        final Connection connection = Connector.getInstance().newConnection();
        final Channel channel = connection.createChannel();
        //设置为每次获取1条消息
        channel.basicQos(1);
        //接收消息
        channel.basicConsume(QUEUE_NAME,AUTO_ACK,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey();
                String expiration = properties.getExpiration();
                long deliveryTag = envelope.getDeliveryTag();
                String msg = new String(body,"UTF-8");
//                MqVO vo = JSON.parseObject(msg,MqVO.class);
                System.out.println(System.currentTimeMillis()+"，消费者：CHANNEL[1]，ROUTER-KEY["+routingKey+"]，消息TAG=["+deliveryTag+"]，TTL=["+expiration+"]，接收到消息内容["+JSON.toJSONString(msg)+"]");
                //确认消费
//                channel.basicAck(deliveryTag,true);
                channel.basicReject(deliveryTag,false);
//                channel.basicNack(deliveryTag,false,true);
//                channel.basicRecover(false);
            }
        });


    }


    public static void main(String[] args) throws Exception {
        //组装消息
//        MqVO msg = new MqVO();
//        msg.setPayNo("110108198411189770");
//        msg.setMerchantNo("merchant001");
//        //发送消息
//        Productor.send(msg);

        Consumer.receive();
    }

}
