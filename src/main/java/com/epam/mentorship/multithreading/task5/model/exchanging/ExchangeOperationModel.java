package com.epam.mentorship.multithreading.task5.model.exchanging;

import com.epam.mentorship.multithreading.task5.model.Currency;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ExchangeOperationModel {

    private Currency input;
    private Currency output;
    private BigDecimal inputValue;
    private BigDecimal outputValue;

}
