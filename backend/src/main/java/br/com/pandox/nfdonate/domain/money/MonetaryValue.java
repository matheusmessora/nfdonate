package br.com.pandox.nfdonate.domain.money;

import java.math.BigDecimal;

public final class MonetaryValue {

    private BigDecimal number;

    private MonetaryValue(Double value) {
        this.number = BigDecimal.valueOf(value);
    }

    public static MonetaryValue of(Double number) {
        return new MonetaryValue(number);
    }

    public BigDecimal number() {
        return number;
    }
}
