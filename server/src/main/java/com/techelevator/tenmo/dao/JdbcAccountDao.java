package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.security.Principal;

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
    public double showCurrentBalance(int id) {
        String sql = "SELECT balance FROM account" +
               // " JOIN tenmo_user ON account.user_id = tenmo_user.user_id" +
                " WHERE user_id = ?";
        double balance = 0;
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
            Account temp = mapRowToAcct(result);
            balance = temp.getBalance();
        } catch (DataAccessException e) {
            return 0.00;
        } return balance;
    }

    @Override
    public double creditAccount() {
        return 0;
    }

    @Override
    public double debitAccount() {
        return 0;
    }

    private Account mapRowToAcct(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccount_id(rowSet.getInt("account_id"));
        account.setBalance(rowSet.getDouble("balance"));
        account.setUser_id(rowSet.getInt("user_id"));
        return account;
    }

}
