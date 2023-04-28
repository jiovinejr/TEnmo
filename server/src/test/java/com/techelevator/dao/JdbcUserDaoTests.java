package com.techelevator.dao;


import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JdbcUserDaoTests extends BaseDaoTests{

    private JdbcUserDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
    }

    @Test
    public void createNewUser() {
        boolean userCreated = sut.create("TEST_USER","test_password");
        Assert.assertTrue(userCreated);
        User user = sut.findByUsername("TEST_USER");
        Assert.assertEquals("TEST_USER", user.getUsername());
    }

    @Test
    public void createNewUserBlankUserName() {
        boolean userCreated = sut.create("","Test_password");
        Assert.assertFalse(userCreated);
    }

    @Test
    public void createNewUserBlankPassword(){
        boolean userCreated = sut.create("TestUser","");
        Assert.assertFalse(userCreated);
    }

    @Test
    public void findIdByUsername(){
        int userId = sut.findIdByUsername("bob");
        Assert.assertEquals(1001, userId);
    }

    @Test
    public void listUsersForTransfer(){
        List<String> listOfUsers = new ArrayList<>();
        listOfUsers = sut.listUsersForTransfer("user");
        Assert.assertEquals(1, listOfUsers.size());
    }

}
