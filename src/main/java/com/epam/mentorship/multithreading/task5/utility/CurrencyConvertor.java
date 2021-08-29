package com.epam.mentorship.multithreading.task5.utility;

import com.epam.mentorship.multithreading.task5.model.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConvertor {

    public static BigDecimal convert(Currency input, BigDecimal value, Currency output) {
        return value.multiply(
                BigDecimal.valueOf(input.getRatioToUSD())).divide(
                BigDecimal.valueOf(output.getRatioToUSD()), RoundingMode.DOWN);
    }

}
