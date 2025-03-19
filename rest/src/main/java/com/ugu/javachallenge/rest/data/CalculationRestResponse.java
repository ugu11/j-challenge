package com.ugu.javachallenge.rest.data;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
public class CalculationRestResponse {
    public BigDecimal result;

    public CalculationRestResponse(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getResult() {
        return result;
    }
}
