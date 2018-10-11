package jun.liu.test;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-13 15:54
 */
public class MqVO {

    private String merchantNo;
    private String payNo;

    public MqVO(){}

    public MqVO(String merchantNo, String payNo){
        this.merchantNo = merchantNo;
        this.payNo = payNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }
}
