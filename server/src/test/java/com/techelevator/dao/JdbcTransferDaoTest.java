package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcTransferDaoTest extends BaseDaoTests{

    private JdbcTransferDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTransferDao(jdbcTemplate);
    }

    @Test
    public void findTransfersByUserId(){
        List<Transfer> listOfTransfers = new ArrayList<>();
        listOfTransfers = sut.findTransfersByUserId(1001);
        Assert.assertEquals(0, listOfTransfers.size());
    }

    @Test
    public void findTransfersByTransferId(){
        Transfer transfer = new Transfer();
        transfer = sut.findTransferByTransferId(1001, 3001);
        Assert.assertNotNull(transfer);
    }

    @Test
    public void createTransfer(){
        Transfer transfer = new Transfer();
        transfer.setTransferAmount(new BigDecimal("50.00"));
        transfer.setSenderUserName("bob");
        transfer.setSenderUserId(1001);
        transfer.setSenderAccountId(2001);
        transfer.setReceiverUserName("user");
        transfer.setReceiverAccountId(2002);
        Transfer resultTransfer = sut.createTransfer(transfer);

        Assert.assertEquals(transfer, resultTransfer);
    }
}
