package com.jun.po;

import java.io.Serializable;
import java.util.List;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-22 14:53
 */
public class UserEntity implements Serializable{

    private String id;
    private String username;
    private String pwd;
    private IdCardEntity idcard;
    private List<FriendEntity> friends;
    private List<RoleEntity> roles;

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<FriendEntity> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendEntity> friends) {
        this.friends = friends;
    }



    public IdCardEntity getIdcard() {
        return idcard;
    }

    public void setIdcard(IdCardEntity idcard) {
        this.idcard = idcard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
