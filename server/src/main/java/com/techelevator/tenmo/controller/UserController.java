package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;


@RestController
public class UserController {

    private UserDao userDao;
    private AccountDao accountDao;
    private TransferDao transferDao;

    public UserController(UserDao userDao, AccountDao accountDao, TransferDao transferDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.transferDao = transferDao;
    }

    //TODO double check that username is not too sensitive of data, may need to fish out ID to complete
    @PreAuthorize("permitAll")
    @GetMapping(path = "/{username}/balance")
    public BigDecimal getUserAccountBalance(@PathVariable String username) {
        BigDecimal balance = new BigDecimal("0.00");
        balance = accountDao.showCurrentBalance(username);
        return balance;
    }

    @PreAuthorize("permitAll")
    @PostMapping(path = "/transfer")
    public Transfer transfer(@RequestBody Transfer transfer) {
        return transferDao.createTransfer(transfer);
    }
}
