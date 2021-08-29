package com.epam.mentorship.multithreading.task5.service;

import com.epam.mentorship.multithreading.task5.dao.UserDao;
import com.epam.mentorship.multithreading.task5.model.Currency;
import com.epam.mentorship.multithreading.task5.model.CurrencyServiceModel;
import com.epam.mentorship.multithreading.task5.model.User;
import com.epam.mentorship.multithreading.task5.model.exchanging.ExchangeOperationModel;
import com.epam.mentorship.multithreading.task5.utility.CurrencyConvertor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
public class CurrencyService {

    public CurrencyService() {
        currencyServiceModel = new CurrencyServiceModel();
        userDao = new UserDao();
    }

    private CurrencyServiceModel currencyServiceModel;
    private UserDao userDao;

    public void updateUserData() {
        currencyServiceModel.setUsers(userDao.readUsersData());
    }

    public void saveUserData() {
        userDao.saveUsersData(currencyServiceModel.getUsers());
    }

    public void setNewCurrencyValue(Currency currency, double value) {
        currency.setRatioToUSD(value);
    }

    public List<User> getUsers() {
        return currencyServiceModel.getUsers();
    }

    public void addUser(User user) {
        currencyServiceModel.addUser(user);
    }

    public void removeUser(User user) {
        currencyServiceModel.remove(user);
    }

    public void convert(ExchangeOperationModel model, User user) {
        model = exchange(model, user);
        if (Objects.nonNull(model)) {
            BigDecimal inputValue = user.getMoney().get(model.getInput());
            user.getMoney().put(model.getInput(), inputValue.subtract(model.getInputValue()));
            inputValue = user.getMoney().get(model.getOutput());
            user.getMoney().put(model.getOutput(), inputValue.add(model.getOutputValue()));
        }
    }

    private ExchangeOperationModel exchange(ExchangeOperationModel request, final User user) {
        if (!validateRequest(request, user)) {
            return null;
        }
        BigDecimal convertedValue = CurrencyConvertor.convert(request.getInput(), request.getInputValue(), request.getOutput());
        request.setOutputValue(convertedValue);
        return request;
    }

    private boolean validateRequest(ExchangeOperationModel request, final User user) {

        BigDecimal userValue = user.getMoney().get(request.getInput());
        if (Objects.isNull(userValue)) {
            return false;
        }
        return request.getInputValue().compareTo(userValue) >= 0;

    }

}

