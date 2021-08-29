package com.epam.mentorship.multithreading.task5.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
public class User {

    private String nickname;
    private Map<Currency, BigDecimal> money;

    public User(Map<Currency, BigDecimal> money) {
        this.money = money;
    }

    public User(String nickname, Map<Currency, BigDecimal> money) {
        this.nickname = nickname;
        this.money = money;
    }
}
