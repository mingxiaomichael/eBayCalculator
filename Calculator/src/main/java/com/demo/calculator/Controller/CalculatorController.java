package com.demo.calculator.Controller;

import com.demo.calculator.model.CalculateRequest;
import com.demo.calculator.model.ChainingRequest;
import com.demo.calculator.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    private final CalculateService calculateService;

    @Autowired
    public CalculatorController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @PostMapping("/basic")
    public BigDecimal calculate(@RequestBody CalculateRequest calculateRequest) {
        return calculateService.calculate(calculateRequest);
    }

    @PostMapping("/chaining")
    public BigDecimal chainingCalculate(@RequestBody ChainingRequest request) {
        return calculateService.chainingCalculate(request);
    }

    // Extensibility: Allow new operations to be added without requiring changes to its existing code
    @GetMapping("/addOperation")
    public void addOperation() {
        calculateService.addOperation();
    }
}
