package com.yq.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;



public class MybatisUtil {

    /**
     * 一个sessionFactory对应一个environment（数据库），所以不要每次运行都new一个新的
     */

    private static ThreadLocal<SqlSession> local = new ThreadLocal();
    /**
     * 构造方法设置为私有的，防止被实例化
     */
    private MybatisUtil(){}

    private static SqlSessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openSession(){
        SqlSession sqlSession = local.get();
        if(sqlSession==null){
            sqlSession = sessionFactory.openSession();
            local.set(sqlSession);
        }

        return sqlSession;
    }

    public static void close(SqlSession sqlSession){
        if(sqlSession!=null){
            sqlSession.close();
            local.remove();
        }
    }
}

