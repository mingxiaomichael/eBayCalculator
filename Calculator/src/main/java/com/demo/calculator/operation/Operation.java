package com.demo.calculator.operation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface Operation {
    BigDecimal calculate(BigDecimal a, BigDecimal b);
}
