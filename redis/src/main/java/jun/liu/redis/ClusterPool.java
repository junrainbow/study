package jun.liu.redis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class ClusterPool {
    
    private static final String CONFIG_FILE_PATH = "local-redis-conf4cluster.properties";
    
    public static JedisCluster pool;
    
    
    static{
        initPool();
    }

    /**
     * 初始化连接池Pool
     */
    private static void initPool(){
        try {
            //加载config
            pool = loadConfig();
        } catch (Exception e) {
            System.out.println("initPool初始化异常！");
            e.printStackTrace();
        }
    }
    
    /**
     * 加载Config
     * @return
     * @throws IOException
     */
    private static JedisCluster loadConfig() throws IOException{
        //加载配置文件
        Properties p = new Properties();
        InputStream is = ClusterPool.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH);
        p.load(is);
        //获取配置信息
        String clusterNode = (String)p.get("clusterNode");
        String[] nodeArray = clusterNode.split(",");
        Set<HostAndPort> clusterNodeSet = new HashSet<HostAndPort>(nodeArray.length);
        for (String host:nodeArray) {
            String[] tmp = host.split(":");
            String ip = tmp[0];
            int port = Integer.valueOf(tmp[1]);
            HostAndPort node = new HostAndPort(ip,port);
            clusterNodeSet.add(node);
        }

        System.out.println("Redis初始化集群节点列表：\n"+ JSON.toJSONString(clusterNodeSet));
        //构建config配置对象
        JedisCluster config = new JedisCluster(clusterNodeSet);
        return config;
    }
    

    
}
