package com.epam.mentorship.multithreading.task5.model;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyServiceModel {
    private List<User> users;

    public void addUser(User user) {
        users.add(user);
    }

    public void remove(User user) {
        users.remove(user);
    }
}
