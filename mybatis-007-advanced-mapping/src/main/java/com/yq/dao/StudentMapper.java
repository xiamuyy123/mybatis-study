package com.yq.dao;

import com.yq.pojo.Student;

import java.util.List;

public interface StudentMapper {

    Student selectByCid(Integer cid);
    Student selectByIdWithStep(Integer id);
    Student selectByIdWithAsso(Integer id);
    Student selectById(Integer id);
}
