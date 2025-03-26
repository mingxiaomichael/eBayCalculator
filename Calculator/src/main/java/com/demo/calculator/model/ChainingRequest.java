package com.demo.calculator.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ChainingRequest {
    private BigDecimal initial;
    private List<Calculation> calculations;

    public BigDecimal getInitial() {
        return initial;
    }

    public List<Calculation> getCalculations() {
        return calculations;
    }
}
