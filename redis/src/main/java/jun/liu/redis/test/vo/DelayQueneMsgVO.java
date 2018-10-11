package jun.liu.redis.test.vo;

import java.util.Date;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-02 21:18
 */
public class DelayQueneMsgVO {

    private Date currentTime;
    private long delayMills;

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public long getDelayMills() {
        return delayMills;
    }

    public void setDelayMills(long delayMills) {
        this.delayMills = delayMills;
    }
}
