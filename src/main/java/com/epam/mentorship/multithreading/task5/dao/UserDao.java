package com.epam.mentorship.multithreading.task5.dao;

import com.epam.mentorship.multithreading.task5.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UserDao {

    private final String JSON_FILE_PATH = "src/main/resources/users.json";

    public void saveUsersData(List<User> userList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(JSON_FILE_PATH), userList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<User> readUsersData() {
        List<User> userList = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            userList = mapper.readValue(new File(JSON_FILE_PATH), new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Objects.isNull(userList) ? null : userList;
    }
}
