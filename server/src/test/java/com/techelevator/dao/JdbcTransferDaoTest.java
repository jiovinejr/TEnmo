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

public class JdbcTransferDaoTest extends BaseDaoTests{

    private JdbcTransferDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTransferDao(jdbcTemplate);
    }

//    @Test
//    public void validateTransferTest() {
//        Transfer transfer = new Transfer(new BigDecimal("50.00"), 1001, 2001,2002);
//        boolean isValid = sut.validateTransfer(transfer);
//        Assert.assertTrue(isValid);
//    }

}
