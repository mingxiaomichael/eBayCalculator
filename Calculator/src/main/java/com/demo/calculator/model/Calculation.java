package com.demo.calculator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class Calculation {
    private String operation;
    private BigDecimal num;

    // Getters and Setters
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }
}
