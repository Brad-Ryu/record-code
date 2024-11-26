package com.brad.mybatistest.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface BoardMapper {
    ArrayList<HashMap<String, Object>> findAll();
}
