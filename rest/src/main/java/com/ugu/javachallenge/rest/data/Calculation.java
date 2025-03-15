package com.ugu.javachallenge.rest.data;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
public class Calculation {
    private final BigDecimal a;
    private final BigDecimal b;
    private final OperationType operationType;
    @Setter
    private BigDecimal result;

    public Calculation(BigDecimal a, BigDecimal b, OperationType operationType) {
        this.a = a;
        this.b = b;
        this.operationType = operationType;
    }
}