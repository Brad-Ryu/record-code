package com.brad.mybatistest.service;

import com.brad.mybatistest.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    public ArrayList<HashMap<String, Object>> findAll() {
        return boardMapper.findAll();
    }
}
