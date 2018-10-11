package com.jun.mapper;

import com.jun.model.RoleModel;
import com.jun.po.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-22 10:36
 */
public interface RoleMapper {

    public RoleEntity getById(long id);

    public RoleEntity getByParam(@Param("param") long id);

    public RoleEntity getByMap(Map<String,String> map);

    public RoleEntity getByEntity(RoleEntity entity);

    public RoleEntity getByModel(RoleModel model);

    public int getCount(long id);

    public void save(RoleEntity entity);

    public RoleEntity getByName(String name);


}
