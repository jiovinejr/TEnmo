package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();
    //TODO added this***
    List<String> listUsersForTransfer(String username);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password);
}
