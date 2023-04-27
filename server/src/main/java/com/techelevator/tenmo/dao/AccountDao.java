package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface AccountDao {

    Account create();

    BigDecimal showCurrentBalance(String username);

    BigDecimal creditAccount(Transfer transfer);

    BigDecimal debitAccount(Transfer transfer);

    BigDecimal findAccountBalanceByUserId(int userId);



}

