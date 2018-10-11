package com.jun.test;

import com.alibaba.fastjson.JSON;
import com.jun.build.DbUtils;
import com.jun.mapper.RoleMapper;
import com.jun.mapper.UserMapper;
import com.jun.model.RoleModel;
import com.jun.po.RoleEntity;
import com.jun.po.UserEntity;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-22 10:59
 */
public class Test {

    public static void main(String[] args) throws Exception {
        SqlSession session = null;
        try {


//        RoleEntity roleEntity = mapper.getById(1);
//        System.out.println(JSON.toJSONString(roleEntity));
//
//        Map<String,String> param = new HashMap<String, String>();
//        param.put("id","1");
//        roleEntity = mapper.getByMap(param);
//        System.out.println(JSON.toJSONString(roleEntity));
//
//        roleEntity = mapper.getByParam(1);
//        System.out.println(JSON.toJSONString(roleEntity));
//
//        RoleEntity entity = new RoleEntity();
//        entity.setId(1);
//        roleEntity = mapper.getByEntity(entity);
//        System.out.println(JSON.toJSONString(roleEntity));
//
//        RoleModel model = new RoleModel();
//        model.setId(1);
//        roleEntity = mapper.getByModel(model);
//        System.out.println(JSON.toJSONString(roleEntity));

//        int count = mapper.getCount(1);
//        System.out.println(count);

//            RoleMapper mapper = session.getMapper(RoleMapper.class);
//            RoleEntity entity = new RoleEntity();
//            entity.setName("junrainbow");
//            mapper.save(entity);
//            System.out.println(JSON.toJSONString(entity));


//            UserMapper mapper = session.getMapper(UserMapper.class);
//            UserEntity entity = new UserEntity();
//            entity.setUsername("junrainbow");
//            entity.setPwd("123qwe");
//            mapper.save(entity);
//            System.out.println(JSON.toJSONString(entity));


            session = DbUtils.getSession();
            UserMapper mapper = session.getMapper(UserMapper.class);
            UserEntity userEntity = mapper.getById("1c01ac45-a5d9-11e8-b5d3-5882a89a3ec1");
            session.commit();
//
//
//            SqlSession session2 = DbUtils.getSession();
//            UserMapper mapper2 = session2.getMapper(UserMapper.class);
//            userEntity = mapper2.getById("1c01ac45-a5d9-11e8-b5d3-5882a89a3ec1");
//            session2.close();
//
//            System.out.println(mapper==mapper2);


//            session = DbUtils.getSession();
//            RoleMapper mapper = session.getMapper(RoleMapper.class);
//            RoleEntity entity = mapper.getByName("管理员");
//            System.out.println(entity.getName());
//            session.commit();
//String sds = "MIICXwIBAAKBgQCyXM0wS6GXzIcNQrKc6SKSFY0gpXFtnWaZA01vlV2r0mIOQYhUpS5jjaq0z/WCKhqirDMFeEKOcz4BqNLE28CjyuAx1CZFqGjg9HxzN+YTt5/U8b6kD1HRRvC2/WxlauSbD9/DKRqWO9Os4BEHKLPLx4GxJxXUMYG/0FC55nW1qwIDAQABAoGBAIO1qcPPJb3dx6DQLutV8TRk+7ku0qr6P4ggey854lj+W3BhjhXLgIz9USCCLK54/wA/HtIcMzHUAebhzvqVuBNAIEJYg67ZZWUs+x4m/smPMsWqojrUklAh8ndRm1sYlHwgGZ09/U8/yJGUf81A1V0k/XLAgQEBxvLuCmO1Z3rBAkEA2Hxb1mKdsUyL2aZB2Mwi/9SDAYqXBYs6cV5C/1C/z9w1WaqGjv07IoevE5QENLUTVWZTyNat3iL1K91azFhLywJBANLrEo+KXvR6+CJyeV13k9Q/Dm7XfM1cMmv+uol66vXl4KuM5cRG2mRi9TJ7+d39z44U3sXZSAHKioZVidAewaECQQCG8QWB3diz0qX91j+HNr++PiYnCM9YWk/kqMAUS640j+wFZ2EWskxxGqiMCKCShih1/CHPt9mK9LlkTUcyxvr/AkEAglQfM/McmZUinqZ9t/ObtRsBoBwnUf4WagUM3sbFSQyyt05o68+cQ0uil39j9nhPOExNG5QXmoO89SUF80QOAQJBAKJBAh0EnRyPI2spb9SbDmF/jDdfwYB71MyO80zPQBY1BX4UGeX6x6EH93lOactLkLGxu4VT97gsyNcJim/kTns=";
//String sds2 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyXM0wS6GXzIcNQrKc6SKSFY0gpXFtnWaZA01vlV2r0mIOQYhUpS5jjaq0z/WCKhqirDMFeEKOcz4BqNLE28CjyuAx1CZFqGjg9HxzN+YTt5/U8b6kD1HRRvC2/WxlauSbD9/DKRqWO9Os4BEHKLPLx4GxJxXUMYG/0FC55nW1qwIDAQAB";
//System.out.println(sds.length());
//System.out.println(sds2.length());

        }finally {
//            session.close();
        }
    }
}
