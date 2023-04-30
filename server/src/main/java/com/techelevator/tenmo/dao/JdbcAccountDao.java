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
        String sql = "SELECT account.user_id, account_id, balance FROM account" +
                " JOIN tenmo_user ON account.user_id = tenmo_user.user_id" +
                " WHERE username = ?";
        Account account = new Account();
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()) {
            account = mapRowToAcct(result);
        }
        return account.getBalance();
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

            System.out.println("No account found");
        }
        return balance;
    }

    @Override
    public boolean validateTransfer(Transfer transfer) {
        boolean isValid = false;
        BigDecimal senderBalance = findAccountBalanceByUserId(transfer.getSenderUserId());
        if (senderBalance.compareTo(transfer.getTransferAmount()) >= 0 && transfer.getTransferAmount().compareTo(new BigDecimal("0")) > 0) {
            isValid = true;
        }
        return isValid;
    }


    private Account mapRowToAcct(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountId(rowSet.getInt("account_id"));
        account.setUserId(rowSet.getInt("user_id"));
        account.setBalance(rowSet.getBigDecimal("balance"));
        return account;
    }

}
