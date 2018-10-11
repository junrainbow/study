package jun.liu.test.test.qa;

import com.alibaba.fastjson.JSON;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-14 13:27
 */
public class MerchantNotify {


    public static void main(String[] args) throws Exception {
        merchantNotifyTest();
    }

    public static void merchantNotifyTest() throws IOException {
        try {
            //基础信息
            String productCode = "1111111_CCB";
            String cashierURL = "http://10.151.30.107:8003/yqt-cashier-app/ccb/callback/merchatNotify/"+productCode;
            String aesKey = "0ac285a4acd448f6";
            //模拟支付信息
            NotifyVO payInfo = new NotifyVO();
            payInfo.setAmount(0.01);
            payInfo.setInnerOrderNo("105000353992575000000004665");
            payInfo.setMerchantNo("1111111110000216");
            payInfo.setOrderStatus("SUCCESS");
            payInfo.setRequestNo("1534144246997scan-alipay");
            //构建请求信息
            String bizContent = JSON.toJSONString(payInfo);
            String encry = AES2.encryptToBase64(bizContent,aesKey);
            String merchantGroupNo = "100000000001";
            List<NameValuePair> param = new ArrayList<NameValuePair>();
            NameValuePair p1 = new BasicNameValuePair("merchantGroupNo",merchantGroupNo);
            NameValuePair p2 = new BasicNameValuePair("bizContent",encry);
            param.add(p1);
            param.add(p2);
            //模拟通知QA-CASHIER
            System.out.println("本次请求URL："+ cashierURL + "\n请求参数："+JSON.toJSONString(param)+"\n");
            HttpPost post = new HttpPost(cashierURL);
            post.setEntity(new UrlEncodedFormEntity(param));
            CloseableHttpClient httpclient = HttpInvokerManager.getHttpClient();
            CloseableHttpResponse response = httpclient.execute(post);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            //打印收银台响应结果
            System.out.println("收银台响应结果： "+result);
        }catch (Throwable t){
            t.printStackTrace();
        }
    }


    public static class NotifyVO{
        private String merchantNo;
        private String requestNo;
        private String innerOrderNo;
        private double amount;
        private String orderStatus;

        public String getMerchantNo() {
            return merchantNo;
        }

        public void setMerchantNo(String merchantNo) {
            this.merchantNo = merchantNo;
        }

        public String getRequestNo() {
            return requestNo;
        }

        public void setRequestNo(String requestNo) {
            this.requestNo = requestNo;
        }

        public String getInnerOrderNo() {
            return innerOrderNo;
        }

        public void setInnerOrderNo(String innerOrderNo) {
            this.innerOrderNo = innerOrderNo;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }

}
