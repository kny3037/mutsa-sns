package com.mutsasnskimnayeong.service;

import org.springframework.stereotype.Service;

@Service
public class AlgorithmService {

    public Integer sumOfDigit(Integer num){

        int sum = 0;

        while (num > 0){
            sum += num%10;
            num/=10;
        }
        return sum;
    }
}
