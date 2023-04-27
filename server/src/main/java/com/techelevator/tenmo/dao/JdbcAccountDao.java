package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;

@Component
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
    public BigDecimal showCurrentBalance(String username) {
        String sql = "SELECT balance FROM account" +
                " JOIN tenmo_user ON account.user_id = tenmo_user.user_id" +
                " WHERE username = ?";
        BigDecimal balance = new BigDecimal("0.00");
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()) {
            balance = result.getBigDecimal("balance");
        }
        return balance;
    }

    @Override
    public BigDecimal creditAccount(Transfer transfer) {
        BigDecimal updatedBalance = new BigDecimal("0");
        String sql = "UPDATE account SET balance = balance + ? WHERE account_id = ? RETURNING balance";
        updatedBalance = jdbcTemplate.queryForObject(sql, BigDecimal.class,
                transfer.getTransferAmount(), transfer.getReceiverAccountId());
        return updatedBalance;
    }

    @Override
    public BigDecimal debitAccount(Transfer transfer) {
        BigDecimal updatedBalance = new BigDecimal("0");
        String sql = "UPDATE account SET balance = balance - ? WHERE account_id = ? RETURNING balance";
        updatedBalance = jdbcTemplate.queryForObject(sql, BigDecimal.class,
                transfer.getTransferAmount(), transfer.getSenderAccountId());
        return updatedBalance;
    }

    @Override
    public BigDecimal findAccountBalanceByUserId(int userId) {
        String sql = "SELECT balance FROM account " +
                " WHERE user_id = ?";
        BigDecimal balance = new BigDecimal("0.00");
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql,userId);
            if(result.next()) {
                balance = result.getBigDecimal("balance");
            }
        } catch (Exception e) {
            //TODO
            System.out.println("this is fucked");
        }
        return balance;
    }


    private Account mapRowToAcct(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountId(rowSet.getInt("account_id"));
        account.setUserId(rowSet.getInt("user_id"));
        if (rowSet.getBigDecimal("balance") == null){
            System.out.println("this null shit");
        } else {
            account.setBalance(rowSet.getBigDecimal("balance"));
        }
        return account;
    }

}
