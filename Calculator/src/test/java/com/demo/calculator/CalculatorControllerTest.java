package com.demo.calculator;

import com.demo.calculator.Controller.CalculatorController;
import com.demo.calculator.model.CalculateRequest;
import com.demo.calculator.model.Calculation;
import com.demo.calculator.model.ChainingRequest;
import com.demo.calculator.service.CalculateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculatorControllerTest {

    @Mock
    private CalculateService calculateService;

    @InjectMocks
    private CalculatorController calculatorController;

    // Test for normal case of basic calculation
    @Test
    void testCalculate() {
        // Prepare request
        CalculateRequest request = new CalculateRequest();
        request.setOperation("ADD");
        request.setNum1(new BigDecimal("10"));
        request.setNum2(new BigDecimal("5"));

        // Mock service layer response
        when(calculateService.calculate(request)).thenReturn(new BigDecimal("15"));

        // Call controller method
        BigDecimal result = calculatorController.calculate(request);

        // Validate result
        assertNotNull(result);
        assertEquals(new BigDecimal("15"), result);
    }

    // Test for chaining calculation with multiple operations
    @Test
    void testChainingCalculate() {
        // Prepare chaining request with initial value and operations
        ChainingRequest request = new ChainingRequest();
        request.setInitial(new BigDecimal("10"));
        request.setCalculations(List.of(
                new Calculation("ADD", new BigDecimal("5")),
                new Calculation("SUBTRACT", new BigDecimal("2"))
        ));

        // Mock service layer response
        when(calculateService.chainingCalculate(request)).thenReturn(new BigDecimal("13"));

        // Call controller method
        BigDecimal result = calculatorController.chainingCalculate(request);

        // Validate result
        assertNotNull(result);
        assertEquals(new BigDecimal("13"), result);
    }

    // Test for add operation method
    @Test
    void testAddOperation() {
        // Here we are testing adding new operations, but since it's not implemented in the controller,
        // this test will focus on verifying no exceptions are thrown during the call.
        doNothing().when(calculateService).addOperation();

        // Call controller method (This would allow new operations to be added dynamically)
        calculatorController.addOperation();

        // Verify the service method was called
        verify(calculateService, times(1)).addOperation();
    }

    // Test edge case where CalculateRequest operation is invalid
    @Test
    void testCalculateInvalidOperation() {
        // Prepare request with invalid operation
        CalculateRequest request = new CalculateRequest();
        request.setOperation("INVALID_OPERATION");
        request.setNum1(new BigDecimal("10"));
        request.setNum2(new BigDecimal("5"));

        // Mock service layer response (simulating invalid operation handling)
        when(calculateService.calculate(request)).thenThrow(new IllegalArgumentException("Invalid operation"));

        // Call controller method and assert exception handling
        assertThrows(IllegalArgumentException.class, () -> calculatorController.calculate(request));
    }

    // Test edge case for division by zero
    @Test
    void testCalculateDivisionByZero() {
        // Prepare request with division operation
        CalculateRequest request = new CalculateRequest();
        request.setOperation("DIVIDE");
        request.setNum1(new BigDecimal("10"));
        request.setNum2(BigDecimal.ZERO);

        // Mock service layer response (simulating divide by zero exception)
        when(calculateService.calculate(request)).thenThrow(new ArithmeticException("Cannot divide by zero"));

        // Call controller method and assert exception handling
        assertThrows(ArithmeticException.class, () -> calculatorController.calculate(request));
    }

    // Test edge case for chainingCalculate with empty calculation list
    @Test
    void testChainingCalculateEmptyList() {
        // Prepare an empty chaining request
        ChainingRequest request = new ChainingRequest();
        request.setInitial(new BigDecimal("10"));
        request.setCalculations(List.of());  // Empty list of calculations
    }
}