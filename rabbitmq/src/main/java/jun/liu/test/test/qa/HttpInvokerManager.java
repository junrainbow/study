package jun.liu.test.test.qa;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-09 10:52
 */
public class HttpInvokerManager {

//    protected static Logger logger = LoggerFactory.getLogger(HttpInvokerManager.class);

    public static PoolingHttpClientConnectionManager cm = null;

    private static CloseableHttpClient httpClient = null;

    static{
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf)
                .register("https", sslsf)
                .build();
        cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(100);

//        RequestConfig requestConfig = RequestConfig.custom()// 配置请求的超时设置
//                .setConnectionRequestTimeout(60000)
//                .setConnectTimeout(60000)
//                .setSocketTimeout(60000)
//                .build();

        //从连接池获得连接
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    public static CloseableHttpClient getHttpClient() throws UnsupportedEncodingException {
        //打印连接池使用情况
//        logger.info("now client pool {}",cm.getTotalStats().toString());
        System.out.println("now client pool "+cm.getTotalStats().toString());
        return httpClient;
    }
}
