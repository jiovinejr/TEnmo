package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcAccountDao implements AccountDao{

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account create() {
        return null;
    }

    @Override
    public double showCurrentBalance() {
        return 0;
    }

    @Override
    public double creditAccount() {
        return 0;
    }

    @Override
    public double debitAccount() {
        return 0;
    }
}
