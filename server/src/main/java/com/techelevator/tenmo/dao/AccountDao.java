package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.util.List;

public interface AccountDao {

    Account create();

    double showCurrentBalance();

    double creditAccount();

    double debitAccount();

}
