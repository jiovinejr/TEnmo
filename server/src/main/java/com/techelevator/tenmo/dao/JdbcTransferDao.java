package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean validateTransfer() {
        return false;
    }

    @Override
    public List<Transfer> findTransfersByUserId() {
        return null;
    }

    @Override
    public List<Transfer> findTransferByTransferId() {
        return null;
    }

    @Override
    public Transfer createTransfer() {
        return null;
    }
}
