package com.yq.dao;

import com.yq.pojo.Car;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarMapper {

    List<Car> getCarByPage(@Param("startIndex")Integer startIndex,@Param("pageSize") Integer pageSize);
    List<Car> getAllCar();
    List<Car> getCarByType(String type);

    List<Car> getCarByOrder(String order);

    List<Car> getCarLike(String type);

    int insert(Car car);

    List<Car> getCarByTypeAndPrice(@Param("type") String type,@Param("price") Double price);

    Map<String,Object> getCarMap(Long id);

    @MapKey("id")
    Map<Long,Map<String,Object>> getCarRetMap();
}
