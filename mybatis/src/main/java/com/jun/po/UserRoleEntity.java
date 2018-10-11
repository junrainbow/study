package com.jun.po;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-22 17:32
 */
public class UserRoleEntity {

    private long id;
    private String userId;
    private long roleId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
