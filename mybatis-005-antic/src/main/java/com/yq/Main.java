package com.yq;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yq.dao.CarMapper;
import com.yq.pojo.Car;
import com.yq.utils.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @Test
    public void test1() throws IOException {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession1 = sessionFactory.openSession();
        SqlSession sqlSession2 = sessionFactory.openSession();
        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        System.out.println(mapper1.getCarByType("新能源"));
//        Car car = new Car(null,"1004","比亚迪汉",160.00,"2022-06-08","新能源汽车");
//
//        mapper.insert(car);
        sqlSession1.close();
        CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);
        System.out.println(mapper2.getCarByType("新能源"));
        sqlSession2.close();

    }
    @Test
    public void test2(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        List<Car> car = mapper.getCarByOrder("desc");
        System.out.println(car);
    }
    @Test
    public void test3(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        List<Car> car = mapper.getCarLike("能源");
        System.out.println(car);
    }
    @Test
    public void test4(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        Car car = new Car(null,"1004","比亚迪汉",160.00,"2022-06-08","新能源汽车");
        int count = mapper.insert( car);
        System.out.println("新增："+count);
        System.out.println(car.getId());
        MybatisUtil.openSession().commit();
    }
    @Test
    public void test5(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        List<Car> car = mapper.getCarByTypeAndPrice("新能源",30D);
        System.out.println(car);
    }

    @Test
    public void test6(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        Map<String, Object> car = mapper.getCarMap(5L);
        System.out.println(car);
    }
    @Test
    public void test7(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        Map<Long, Map<String, Object>> carRetMap = mapper.getCarRetMap();
        System.out.println(carRetMap);
    }
    @Test
    public void test8(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        System.out.println(mapper.getAllCar());
    }
    @Test
    public void testPage(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        int pageNum = 2;
        int pageSize = 3;
        System.out.println(mapper.getCarByPage((pageNum-1)*pageSize,pageSize));
    }
    @Test
    public void testPageByHelper(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        int pageNum = 2;
        int pageSize = 3;
        PageHelper.startPage(pageNum,pageSize);
        List<Car> cars = mapper.getAllCar();
        System.out.println(cars);
        PageInfo<Car> pageInfo = new PageInfo<>(cars);
        System.out.println(pageInfo);
    }
}