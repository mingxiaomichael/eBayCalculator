package com.demo.calculator.operation;

import com.demo.calculator.operation.Impl.AdditionOperation;
import com.demo.calculator.operation.Impl.DivisionOperation;
import com.demo.calculator.operation.Impl.MultiplicationOperation;
import com.demo.calculator.operation.Impl.SubtractionOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OperationFactory {

    private final Map<String, Operation> operations = new HashMap<>();

    public OperationFactory() {
        operations.put("ADD", new AdditionOperation());
        operations.put("SUBTRACT", new SubtractionOperation());
        operations.put("MULTIPLY", new MultiplicationOperation());
        operations.put("DIVIDE", new DivisionOperation());
    }

    public void addOperation(String operationName, Operation operation) {
        operations.put(operationName.toUpperCase(), operation);
    }

    public Operation getOperation(String operationName) {
        Operation operation = operations.get(operationName.toUpperCase());
        if (operation == null) {
            throw new IllegalArgumentException("Invalid operation type");
        }
        return operation;
    }
}