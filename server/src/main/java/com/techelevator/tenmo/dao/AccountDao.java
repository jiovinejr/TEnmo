package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.security.Principal;
import java.util.List;

public interface AccountDao {

    Account create();

    double showCurrentBalance(int id);

    double creditAccount();

    double debitAccount();

}
