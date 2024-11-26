package com.brad.mybatistest.controller;

import com.brad.mybatistest.response.ResponseDto;
import com.brad.mybatistest.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/board/")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    public ResponseEntity<?> findAll() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResultCode("S0001");
        responseDto.setRes(boardService.findAll());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
