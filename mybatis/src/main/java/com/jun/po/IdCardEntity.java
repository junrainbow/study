package com.jun.po;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-22 15:06
 */
public class IdCardEntity {

    private long id;
    private long cardNo;
    private String userId;
    private IdCardTypeEntity idCardType;




    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public IdCardTypeEntity getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(IdCardTypeEntity idCardType) {
        this.idCardType = idCardType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCardNo() {
        return cardNo;
    }

    public void setCardNo(long cardNo) {
        this.cardNo = cardNo;
    }
}
