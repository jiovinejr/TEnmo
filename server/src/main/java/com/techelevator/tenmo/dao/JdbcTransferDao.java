package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
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
    public List<Transfer> findTransfersByUserId(int userId) {
        String sql = "SELECT transfer_id, transfer_amount, sender_account_id, tenmo_user.username, receiver_account_id" +
                " FROM transfer JOIN account ON transfer.receiver_account_id = account.account_id" +
                " JOIN tenmo_user ON account.user_id = tenmo_user.user_id" +
                " WHERE transfer.user_id = ? OR tenmo_user.user_id = ?";
        List<Transfer> transferList = new ArrayList<>();

        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId, userId);
            while(result.next()){
                transferList.add(mapRowToTransfer(result));
            }
        } catch (Exception e) {
            //TODO
            System.out.println("Try again motherfucker");
        }

        return transferList;
    }

    @Override
    public Transfer findTransferByTransferId(int transferId) {
        return null;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        String sqlReceiver = "SELECT username FROM tenmo_user JOIN account on tenmo_user.user_id = account.user_id" +
                " WHERE account_id = ?";
        try {
        String receiverUserName = jdbcTemplate.queryForObject(sqlReceiver, String.class, transfer.getReceiverAccountId());
        transfer.setReceiverUserName(receiverUserName);

        String sqlSender = "SELECT username from tenmo_user WHERE user_id = ?";
        String senderUserName = jdbcTemplate.queryForObject(sqlSender, String.class, transfer.getSenderUserId());
        transfer.setSenderUserName(senderUserName);

        String sql = "INSERT INTO transfer (user_id, sender_account_id, receiver_account_id, transfer_amount)" +
                " VALUES (?, ?, ?, ?) RETURNING transfer_id";
        int newTransferId;

            newTransferId = jdbcTemplate.queryForObject(sql, Integer.class, transfer.getSenderUserId(),
                    transfer.getSenderAccountId(), transfer.getReceiverAccountId(), transfer.getTransferAmount());
            transfer.setTransferId(newTransferId);
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
        return transfer;
    }

    private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setSenderUserId(rs.getInt("user_id"));
        transfer.setSenderAccountId(rs.getInt("sender_account_id"));
        transfer.setReceiverAccountId(rs.getInt("receiver_account_id"));
        transfer.setTransferAmount(rs.getBigDecimal("transfer_amount"));
        return transfer;
    }
}

