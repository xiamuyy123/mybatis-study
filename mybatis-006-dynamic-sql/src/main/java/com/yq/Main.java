package com.yq;

import com.yq.dao.CarMapper;
import com.yq.pojo.Car;
import com.yq.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @Test
    public void test1(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        mapper.getCarByParams("",20.0,"").forEach(
                car -> System.out.println(car)
        );
    }
    @Test
    public void test2(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        mapper.getCarByParamsWithWhere("比亚迪",20.0,"新能源").forEach(
                car -> System.out.println(car)
        );
    }
    @Test
    public void test3(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        Car car = new Car(1L,"1001","丰田",null,null,"新能源");
        mapper.updateCar(car);
        MybatisUtil.openSession().commit();
    }
    @Test
    public void test4(){
        CarMapper mapper = MybatisUtil.openSession().getMapper(CarMapper.class);
        mapper.getCarByParamsWithChoose("",20.0,"新能源").forEach(
                car -> System.out.println(car)
        );
    }
    @Test
    public void test5(){
        SqlSession sqlSession = MybatisUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.deleteByIds(new Long[]{16L,17L});
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();


    }
    @Test
    public void test6(){
        SqlSession sqlSession = MybatisUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> list  = new ArrayList<>();
        list.add(new Car(null,"1004","比亚迪汉1",160.00,"2022-06-08","新能源汽车"));
        list.add(new Car(null,"1004","比亚迪汉2",160.00,"2022-06-08","新能源汽车"));
        list.add(new Car(null,"1004","比亚迪汉3",160.00,"2022-06-08","新能源汽车"));
        int count = mapper.insertCars(list);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
}