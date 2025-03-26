package com.demo.calculator.service;

import com.demo.calculator.model.CalculateRequest;
import com.demo.calculator.model.Calculation;
import com.demo.calculator.model.ChainingRequest;
import com.demo.calculator.operation.Operation;
import com.demo.calculator.operation.OperationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalculateService {

    private final OperationFactory operationFactory;

    @Autowired
    public CalculateService(OperationFactory operationFactory) {
        this.operationFactory = operationFactory;
    }

    public BigDecimal calculate(CalculateRequest calculateRequest) {
        Operation operation = operationFactory.getOperation(calculateRequest.getOperation());
        return operation.calculate(calculateRequest.getNum1(), calculateRequest.getNum2());
    }

    public BigDecimal chainingCalculate(ChainingRequest request) {
        if (request.getCalculations().isEmpty()) {
            throw new IllegalArgumentException("Items list cannot be empty");
        }
        BigDecimal result = request.getInitial();
        List<Calculation> calculations = request.getCalculations();
        for (Calculation calculation : calculations) {
            Operation operation = operationFactory.getOperation(calculation.getOperation());
            result = operation.calculate(result, calculation.getNum());
        }
        return result;
    }

    public void addOperation() {
        operationFactory.addOperation("MOD", new Operation() {
            @Override
            public BigDecimal calculate(BigDecimal a, BigDecimal b) {
                if (b.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a.remainder(b);
            }
        });
    }
}