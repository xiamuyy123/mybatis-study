package com.yq;

import com.yq.utils.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ConfigurationTest {
    @Test
    public void testEnv() throws IOException {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"),"yyq");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("insertCar");
        sqlSession.commit();
        sqlSession.close();
    }
}
