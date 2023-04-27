package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
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
    public BigDecimal creditAccount() {
        return null;
    }

    @Override
    public BigDecimal debitAccount() {
        return null;
    }

    @Override
    public int findAccountByUserId(int userId) {
        String sql = "SELECT account_id FROM account " +
                " WHERE user_id = ?";
        SqlRowSet result;

        try{
            result = jdbcTemplate.queryForRowSet(sql,userId);
            if(result.next()) {
                return result.getInt("account_id");
            }
        } catch (Exception e) {
            //TODO
            System.out.println("this is fucked");
        }
        return 0;
    }


    private Account mapRowToAcct(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountId(rowSet.getInt("account_id"));
        account.setUserId(rowSet.getInt("user_id"));
        account.setBalance(rowSet.getBigDecimal("balance"));
        return account;
    }

}
