package com.ugu.javachallenge.rest.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Data
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

    public boolean equals(Calculation o) {
        // Checks if any of the numbers or operationType differs from o
        if (!this.a.equals(o.getA())
                || !this.b.equals(o.getB())
                || this.operationType != o.getOperationType()) return false;
        // Checks if both results are null. If they are, then this == o
        if ((this.result == null && o.getResult() == null)) return true;

        // Checks if result is not null and compares values
        assert this.result != null;
        return this.result.equals(o.getResult());
    }
}