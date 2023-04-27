package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private UserDao userDao;
    private AccountDao accountDao;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public boolean validateTransfer(BigDecimal transferAmount) {
        boolean isValid = false;
        Account account = new Account();
        BigDecimal currentUserBalance = account.getBalance();

        if (currentUserBalance.compareTo(transferAmount) > 0) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    public List<Transfer> findTransfersByUserId() {
        return null;
    }

    @Override
    public Transfer findTransferByTransferId(int transferId) {
        return null;
    }

    @Override
    public Transfer createTransfer(BigDecimal transferAmount, String receiverName, Principal principal) {

        if(validateTransfer(transferAmount)) {
           String sql = "INSERT INTO transfer (user_id, sender_account_id, receiver_account_id, transfer_amount)" +
                   " VALUES (?, ?, ?, ?) RETURNING transfer_id";
           int newTransferId;

           try{
               newTransferId = jdbcTemplate.queryForObject(sql, Integer.class, userDao.findIdByUsername(principal.getName()),
                       accountDao.findAccountByUserId(userDao.findIdByUsername(principal.getName())),
                       accountDao.findAccountByUserId(userDao.findIdByUsername(receiverName)), transferAmount);
               return findTransferByTransferId(newTransferId);

           } catch (Exception e) {
               //TODO
               System.out.println("Also fucked");
           }


        }

        return null;
    }
}
