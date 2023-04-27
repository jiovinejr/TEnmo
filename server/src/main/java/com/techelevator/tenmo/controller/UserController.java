package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/balance")
    public BigDecimal getUserAccountBalance(Principal principal) {
        BigDecimal balance = new BigDecimal("0.00");
        balance = accountDao.showCurrentBalance(principal.getName());
        return balance;
    }


    @PreAuthorize("permitAll")
    @PostMapping(path = "/transfer")
    public Transfer transfer(@RequestBody Transfer transfer) {
        BigDecimal senderBalance = accountDao.findAccountBalanceByUserId(transfer.getSenderUserId());
        // senderbalance > transferAmount
        if (senderBalance.compareTo(transfer.getTransferAmount()) > 0 && transfer.getSenderAccountId() != transfer.getReceiverAccountId()) {
            accountDao.creditAccount(transfer);
            accountDao.debitAccount(transfer);
            return transferDao.createTransfer(transfer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds");
        }
    }
}
