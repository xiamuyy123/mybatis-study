package com.yq.dao;

import com.yq.pojo.Clazz;

import java.util.List;

public interface ClazzMapper {

    Clazz selectClazzWithStep(Integer cid);
    Clazz selectClazzWithStus(Integer cid);
    Clazz selectClazzById(Integer cid);
}
