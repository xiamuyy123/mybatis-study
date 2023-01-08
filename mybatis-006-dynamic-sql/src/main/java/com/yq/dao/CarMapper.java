package com.yq.dao;

import com.yq.pojo.Car;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarMapper {

    int insertCars(@Param("cars")List<Car> cars);
    int deleteByIds(@Param("ids") Long[] ids);
    List<Car> getCarByParamsWithChoose(@Param("brand") String brand,@Param("guidePrice") Double guidePrice,@Param("carType") String carType);

    int updateCar(Car car);
    List<Car> getCarByParamsWithWhere(@Param("brand") String brand,@Param("guidePrice") Double guidePrice,@Param("carType") String carType);

    List<Car> getCarByParams(@Param("brand") String brand,@Param("guidePrice") Double guidePrice,@Param("carType") String carType);
}
