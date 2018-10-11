package jun.liu.redis.test.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-02 16:55
 */
public class UserInfoVO implements Serializable {

    public String username;
    public String pwd;
    public Date lastTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
