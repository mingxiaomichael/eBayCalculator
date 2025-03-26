package com.demo.calculator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class CalculateRequest {
    private String operation;
    private BigDecimal num1;
    private BigDecimal num2;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public BigDecimal getNum1() {
        return num1;
    }

    public void setNum1(BigDecimal num1) {
        this.num1 = num1;
    }

    public BigDecimal getNum2() {
        return num2;
    }

    public void setNum2(BigDecimal num2) {
        this.num2 = num2;
    }
}
