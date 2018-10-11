package com.jun.mapper;

import com.jun.model.RoleModel;
import com.jun.po.IdCardEntity;
import com.jun.po.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-22 10:36
 */
public interface IdCardMapper {

    public IdCardEntity getById(long id);

    public IdCardEntity getByUserId(long id);





}
