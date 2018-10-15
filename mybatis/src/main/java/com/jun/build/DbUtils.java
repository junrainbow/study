package com.jun.build;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author junrainbow
 * @Description
 * @Date:Create in 2018-08-22 11:02
 */
public class DbUtils {

    private static SqlSessionFactory sessionFactory;
    static{
        InputStream in= null;
        try {
            in= Resources.getResourceAsStream("mybatis-config.xml");
            sessionFactory=new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    public static SqlSession getSession(){
        //master code
        return sessionFactory.openSession();
    }
}
