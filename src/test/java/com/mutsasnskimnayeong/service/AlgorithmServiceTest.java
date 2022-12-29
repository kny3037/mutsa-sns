package com.mutsasnskimnayeong.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmServiceTest {

    AlgorithmService algorithmService = new AlgorithmService();

    @Test
    @DisplayName("자릿 수 합 구하기")
    void sumOfDigit() {
        assertEquals(21, algorithmService.SumOfDigit(687));
        assertEquals(22, algorithmService.SumOfDigit(787));
        assertEquals(5, algorithmService.SumOfDigit(11111));
        assertEquals(0, algorithmService.SumOfDigit(0));
    }
}