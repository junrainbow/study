package com.jun.mapper;

import com.jun.po.FriendEntity;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-22 10:36
 */
public interface FriendMapper {

    public FriendEntity getById(long id);

    public FriendEntity getByUserId(String id);






}
