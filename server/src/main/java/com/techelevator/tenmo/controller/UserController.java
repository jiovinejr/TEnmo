package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;


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

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/balance")
    public BigDecimal getUserAccountBalance(Principal principal) {
        //BigDecimal balance = new BigDecimal("0.00");
        BigDecimal balance = accountDao.showCurrentBalance(principal.getName());
        return balance;
    }

    //TODO updated this***
    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/get-users")
    public List<String> getListOfUsersForTransfer(Principal principal) {
        return userDao.listUsersForTransfer(principal.getName());
    }

    //TODO updated this***
    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/transfer")
    public Transfer transfer(@RequestBody Transfer transfer) {
        if (transfer.getSenderAccountId() != transfer.getReceiverAccountId()) {
            // senderbalance > transferAmount
            if (accountDao.validateTransfer(transfer)) {
                accountDao.creditAccount(transfer);
                accountDao.debitAccount(transfer);
                return transferDao.createTransfer(transfer);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot process request (same account)");
        }
    }
}