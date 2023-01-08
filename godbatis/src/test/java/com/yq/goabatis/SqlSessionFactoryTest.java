package com.yq.goabatis;

import com.yq.godbatis.core.SqlSession;
import com.yq.godbatis.core.SqlSessionFactory;
import com.yq.godbatis.core.SqlSessionFactoryBuilder;
import com.yq.godbatis.pojo.User;
import com.yq.godbatis.utils.Resources;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class SqlSessionFactoryTest {
    @Test
    public void testSelectOne() throws DocumentException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        User user = (User) sqlSession.selectOne("user.selectOne", "1");
        sqlSession.commit();
        sqlSession.close();
        System.out.println(user);

    }

    @Test
    public void testInsert() throws DocumentException, SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        User user = new User("11","aaaaa","12");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        int insert = sqlSession.insert("user.insertUser", user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(insert);

    }
    @Test
    public void testBuild() throws DocumentException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("godbatis-config.xml"));
        System.out.println(sqlSessionFactory);
    }

    @Test
    public void test(){
        String sql = "insert into t_car values (#{id},#{name})";
        int fromIndex = 0;
        int index = 1;
        while (true){
            int jingIndex = sql.indexOf("#",fromIndex);
            if(jingIndex<0){
                break;
            }
            int youkuohaoIndex  = sql.indexOf("}",fromIndex);
            String propertyName= sql.substring(jingIndex + 2, youkuohaoIndex);
            System.out.println(propertyName);
            String methodName = "get" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
            System.out.println(methodName);
            fromIndex = youkuohaoIndex+1;
            index++;

        }
    }

}
