package com.demo.calculator.operation.Impl;

import com.demo.calculator.operation.Operation;

import java.math.BigDecimal;

public class AdditionOperation implements Operation {
    @Override
    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }
}
