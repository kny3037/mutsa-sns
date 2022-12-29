package com.mutsasnskimnayeong.controller;

import com.mutsasnskimnayeong.service.AlgorithmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class HelloController {

    private final AlgorithmService algorithmService;

    @GetMapping("/hello")
    public String hello(){
        return "김나영";
    }

    @GetMapping("/hello/{num}")
    public Integer sum(@PathVariable Integer num){
        return algorithmService.SumOfDigit(num);
    }
}
