package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
    public List<Transfer> findTransfersByUserId() {
        return null;
    }

    @Override
    public Transfer findTransferByTransferId(int transferId) {
        return null;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer (user_id, sender_account_id, receiver_account_id, transfer_amount)" +
                " VALUES (?, ?, ?, ?) RETURNING transfer_id";
        int newTransferId;
        try {
            newTransferId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getSenderUserId(),
                    transfer.getSenderAccountId(), transfer.getReceiverAccountId(), transfer.getTransferAmount());
            transfer.setTransferId(newTransferId);
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
        return transfer;
    }
}

