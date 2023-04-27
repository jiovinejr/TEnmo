package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface AccountDao {

    Account create();

    BigDecimal showCurrentBalance(String username);

    BigDecimal creditAccount();

    BigDecimal debitAccount();

    BigDecimal findAccountBalanceByUserId(int userId);



}
