package jun.liu.test;

import com.rabbitmq.client.ConnectionFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-13 12:51
 */
public class Connector {

    private static final String CONFIG_FILE_PATH = "local-connect.properties";

    private static String host;

    private static int port;

    private static String username;

    private static String password;

    private static String vhost;

    private static ConnectionFactory connectFactory;

    private static class InnerClass{
        public static final Connector connector = new Connector();
    }

    private Connector(){
        try {
            //加载配置文件
            loadConfig();
            //构建连接池
            connectFactory = new ConnectionFactory();
            connectFactory.setHost(host);
            connectFactory.setPort(port);
            connectFactory.setUsername(username);
            connectFactory.setPassword(password);
            connectFactory.setVirtualHost(vhost);
            System.out.println("host:"+host);
            System.out.println("port:"+port);
            System.out.println("username:"+username);
            System.out.println("password:"+password);
            System.out.println("vhost:"+vhost);
        }catch (Throwable t){
            System.out.println("初始化AMPQ连接器异常");
            t.printStackTrace();
        }
    };

    public static ConnectionFactory getInstance(){
        Connector connector = InnerClass.connector;
        return connectFactory;
    }



    private void loadConfig(){
        try {
            //加载配置文件
            Properties prop = new Properties();
            InputStream is = Connector.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH);
            prop.load(is);
            //获取配置信息
            host = (String)prop.get("host");
            username = (String)prop.get("username");
            password = (String)prop.get("password");
            vhost = (String)prop.get("vhost");
            port = Integer.valueOf((String)prop.get("port"));
        }catch (Throwable t){
            System.out.println("初始化AMPQ配置文件异常");
        }
    }
}
