package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    private UserDao userDao;
    private AccountDao accountDao;
    private TransferDao transferDao;


    //TODO double check that username is not too sensitive of data, may need to fish out ID to complete
    @GetMapping(path = "/user/{id}/balance")
    public double getUserAccountBalance(@PathVariable int id) {
        double balance = 0.00;
        balance = accountDao.showCurrentBalance(id);
        return balance;
    }
}
