package com.epam.mentorship.multithreading.task5.model;

import lombok.Getter;

@Getter
public enum Currency {

    UAH(0.3), USD(1), EUR(1.2);

    Currency(double ratioToUSD) {
        this.ratioToUSD = ratioToUSD;
    }

    public void setRatioToUSD(double ratioToUSD) {
        this.ratioToUSD = ratioToUSD;
    }

    private double ratioToUSD;


}
