package com.ugu.javachallenge.rest.data;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@Getter
public class CalculationRestResponse {
    public BigDecimal result;

    public CalculationRestResponse(BigDecimal result) {
        this.result = result;
    }
}
