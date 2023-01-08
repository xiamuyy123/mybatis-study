package com.yq;

import com.yq.mybatis.mapper.CarMapper;
import com.yq.mybatis.pojo.CarExample;
import com.yq.mybatis.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!111111111122222" +
                "3333333333333" +
                "4444444444444");
    }

    @Test
    public void test1(){
        SqlSession sqlSession = MybatisUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);

        //1.查询单个
        System.out.println(mapper.selectByPrimaryKey(2L));
        //2.查询所有
        System.out.println(mapper.selectByExample(null));
        //3.条件查询
        CarExample carExample = new CarExample();
        carExample.createCriteria().andBrandEqualTo("比亚迪秦")
                        .andGuidePriceGreaterThan(new BigDecimal(10));

        System.out.println(mapper.selectByExample(carExample));
        sqlSession.close();
    }
}