package com.jun.mapper;

import com.jun.model.RoleModel;
import com.jun.po.RoleEntity;
import com.jun.po.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-22 10:36
 */
public interface UserMapper {


    public void save(UserEntity entity);

    public UserEntity getById(String id);

    public UserEntity getUserInfoById(String id);

    public UserEntity dynamicSql(String id);

    public List<UserEntity> getUserListByIds(@Param("name") String name,@Param("ids") List<String> ids);

}
