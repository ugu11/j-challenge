package com.ugu.javachallenge.calculator.controler;

import com.ugu.javachallenge.calculator.data.Calculation;
import com.ugu.javachallenge.calculator.data.OperationType;

import java.math.BigDecimal;

public class CalculatorController {
    private BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    private BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    private BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    private BigDecimal divide(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }

    public Calculation calculate(Calculation calculation) {
        switch (calculation.getOperationType()) {
            case OperationType.SUM -> calculation.setResult(this.add(calculation.getA(), calculation.getB()));
            case OperationType.SUBTRACTION -> calculation.setResult(this.subtract(calculation.getA(), calculation.getB()));
            case OperationType.MULTIPLICATION -> calculation.setResult(this.multiply(calculation.getA(), calculation.getB()));
            case OperationType.DIVISION -> calculation.setResult(this.divide(calculation.getA(), calculation.getB()));
        }

        return calculation;
    }
}
