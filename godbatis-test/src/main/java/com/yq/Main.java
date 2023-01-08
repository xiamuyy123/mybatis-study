package com.yq;

import com.yq.godbatis.core.SqlSession;
import com.yq.godbatis.core.SqlSessionFactory;
import com.yq.godbatis.core.SqlSessionFactoryBuilder;
import com.yq.godbatis.pojo.User;
import com.yq.godbatis.utils.Resources;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    @Test
    public void testSelectOne() throws DocumentException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        User user = (User) sqlSession.selectOne("user.selectOne", "1");
        sqlSession.commit();
        sqlSession.close();
        System.out.println(user);

    }
}