package com.yq;

import com.utils.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("insertCar");
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        sqlSession.insert("insertCar");
        sqlSession.commit();
        sqlSession.close();
    }
}
