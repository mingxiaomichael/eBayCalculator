package com.demo.calculator.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ChainingRequest {
    private BigDecimal initial;
    private List<Calculation> calculations;

    public ChainingRequest() {
    }

    public ChainingRequest(BigDecimal initial, List<Calculation> calculations) {
        this.initial = initial;
        this.calculations = calculations;
    }

    public BigDecimal getInitial() {
        return initial;
    }

    public List<Calculation> getCalculations() {
        return calculations;
    }

    public void setCalculations(List<Calculation> calculations) {
        this.calculations = calculations;
    }

    public void setInitial(BigDecimal initial) {
        this.initial = initial;
    }
}
