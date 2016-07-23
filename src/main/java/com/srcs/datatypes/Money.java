package com.srcs.datatypes;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by santhosh on 09/07/16.
 */
public class Money {
    private BigDecimal value;

    private Money(BigDecimal value){
        this.value = value;
    }

    public static Money newMoney(double value){
        return new Money(new BigDecimal(value));
    }

    public String formattedValue(){
        return value.setScale(2, RoundingMode.CEILING).toPlainString();
    }
}
