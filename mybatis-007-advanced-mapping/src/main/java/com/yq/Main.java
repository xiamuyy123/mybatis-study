package com.yq;

import com.yq.dao.ClazzMapper;
import com.yq.dao.StudentMapper;
import com.yq.pojo.Student;
import com.yq.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @Test
    public void test1() throws InterruptedException {
        SqlSession sqlSession =  MybatisUtil.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println(mapper.selectById(2));

        new Thread(
                ()->{
                    SqlSession sqlSession1 =  MybatisUtil.openSession();
                    StudentMapper mapper1 = sqlSession1.getMapper(StudentMapper.class);
                    System.out.println(mapper1.selectById(2));

                    sqlSession1.close();

                }
                ,"th1").start();
        Thread.sleep(10000);
        sqlSession.close();
    }
    @Test
    public void test2(){
        SqlSession sqlSession =  MybatisUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println(mapper.selectByIdWithAsso(2));
        sqlSession.close();
    }
    @Test
    public void test3(){
        SqlSession sqlSession =  MybatisUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println(mapper.selectByIdWithStep(2));
        sqlSession.close();
    }
    @Test
    public void test4(){
        SqlSession sqlSession =  MybatisUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdWithStep(2);
        System.out.println(student.getSname());
        System.out.println(student.getClazz());
        sqlSession.close();
    }
    @Test
    public void test5(){
        SqlSession sqlSession =  MybatisUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        System.out.println(mapper.selectClazzWithStus(1001));
        sqlSession.close();
    }
    @Test
    public void test6(){
        SqlSession sqlSession =  MybatisUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        System.out.println(mapper.selectClazzWithStep(1001));
        sqlSession.close();
    }
}