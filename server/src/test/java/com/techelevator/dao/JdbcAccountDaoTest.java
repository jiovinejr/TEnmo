package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcAccountDaoTest extends BaseDaoTests {

    private JdbcAccountDao sut;



    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcAccountDao(jdbcTemplate);

    }



    @Test
    public void showCurrentBalanceTest(){
        BigDecimal result = sut.showCurrentBalance("bob");
        Assert.assertEquals(new BigDecimal("1000.00"), result);
    }

    @Test
    public void showCurrentBalanceTest_bad_username_null(){
        BigDecimal result = sut.showCurrentBalance("jimmy");
        Assert.assertNull(result);
    }

    @Test
    public void creditAccountTest(){
        Transfer transfer = new Transfer();
        transfer.setTransferAmount(new BigDecimal("50.00"));
        transfer.setSenderUserName("bob");
        transfer.setSenderUserId(1001);
        transfer.setSenderAccountId(2001);
        transfer.setReceiverUserName("user");
        transfer.setReceiverAccountId(2002);

        BigDecimal newBalance = sut.creditAccount(transfer);
        Assert.assertEquals(new BigDecimal("1050.00"), newBalance);
    }

    @Test
    public void debitAccountTest() {
        Transfer transfer = new Transfer();
        transfer.setTransferAmount(new BigDecimal("50.00"));
        transfer.setSenderUserName("bob");
        transfer.setSenderUserId(1001);
        transfer.setSenderAccountId(2001);
        transfer.setReceiverUserName("user");
        transfer.setReceiverAccountId(2002);

        BigDecimal newBalance = sut.debitAccount(transfer);
        Assert.assertEquals(new BigDecimal("950.00"), newBalance);
    }

    @Test
    public void findAccountBalanceByUserIdTest(){
        BigDecimal balance = sut.findAccountBalanceByUserId(1001);
        Assert.assertEquals(new BigDecimal("1000.00"), balance);
    }

    @Test
    public void findAccountBalanceByUserIdTest_bad_userId(){
        BigDecimal balance = sut.findAccountBalanceByUserId(1010);
        Assert.assertEquals(new BigDecimal("0.00"), balance);
    }

    @Test
    public void validateTransferTest(){
        Transfer transfer = new Transfer();
        transfer.setTransferAmount(new BigDecimal("50.00"));
        transfer.setSenderUserName("bob");
        transfer.setSenderUserId(1001);
        transfer.setSenderAccountId(2001);
        transfer.setReceiverUserName("user");
        transfer.setReceiverAccountId(2002);

        boolean result = sut.validateTransfer(transfer);
        Assert.assertTrue(result);
    }

    @Test
    public void validateTransferTest_too_much_money(){
        Transfer transfer = new Transfer();
        transfer.setTransferAmount(new BigDecimal("5000.00"));
        transfer.setSenderUserName("bob");
        transfer.setSenderUserId(1001);
        transfer.setSenderAccountId(2001);
        transfer.setReceiverUserName("user");
        transfer.setReceiverAccountId(2002);

        boolean result = sut.validateTransfer(transfer);
        Assert.assertFalse(result);
    }

    @Test
    public void validateTransferTest_same_account(){
        Transfer transfer = new Transfer();
        transfer.setTransferAmount(new BigDecimal("50.00"));
        transfer.setSenderUserName("bob");
        transfer.setSenderUserId(1001);
        transfer.setSenderAccountId(2001);
        transfer.setReceiverUserName("bob");
        transfer.setReceiverAccountId(2001);

        boolean result = sut.validateTransfer(transfer);
        Assert.assertFalse(result);
    }

    }
