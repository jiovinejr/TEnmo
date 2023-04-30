package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserDao {

    List<User> findAll();

    List<String> listUsersForTransfer(String username);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(@Valid String username, @Valid String password);
}
