package edu.palevobot.dao;

import edu.palevobot.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserJdbcDaoTest {
    JdbcDao jdbcDao;
    User user;
    @Before
    public void setUp() throws Exception {
        jdbcDao = (JdbcDao) new UserDaoFactory().getDao("jdbc");
        user = new User(23, "19.09.2018 Test");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInsert() throws SQLException, IOException {
        jdbcDao.insert(user);
    }

}