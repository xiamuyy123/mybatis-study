package com.yq;

import com.yq.pojo.Car;
import com.yq.utils.MybatisUtil;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

    }
    @Test
    public void test1(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        Map<String,Object> map = new HashMap<>();
        map.put("carNum","1004");
        map.put("brand","比亚迪汉");
        map.put("guidePrice",160.00);
        map.put("produceTime","2022-06-08");
        map.put("carType","新能源汽车");
        int count = sqlSession.insert("insertCar", map);
        System.out.println("新增："+count);
        sqlSession.commit();

    }
    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        Car car = new Car(null,"1005","比亚迪秦",30.0,"2020-10-20","新能源");
        int count = sqlSession.insert("insertCar", car);
        System.out.println("新增："+count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void test3(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        int count = sqlSession.delete("deleteCar",14);
        System.out.println("删除："+count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void test4(){
        Car car = new Car(4L,"1005","比亚迪秦",30.0,"2020-10-20","新能源");

        SqlSession sqlSession = MybatisUtil.getSqlSession();
        int count = sqlSession.update("updateCar",car);
        System.out.println("更新："+count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testSelect(){


        SqlSession sqlSession = MybatisUtil.getSqlSession();
        Object o = sqlSession.selectOne("selectOne", 1);
        System.out.println(o);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testSelectAll(){


        SqlSession sqlSession = MybatisUtil.getSqlSession();
        List<Object> all = sqlSession.selectList("selectAll");
        System.out.println(all);
        sqlSession.close();
    }

}