package com.demo.calculator.operation.Impl;

import com.demo.calculator.operation.Operation;

import java.math.BigDecimal;

public class DivisionOperation implements Operation {
    @Override
    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a.divide(b, 5, BigDecimal.ROUND_HALF_UP);
    }
}
