package edu.palevobot.dao;

import edu.palevobot.dao.user.UserDaoFactory;
import edu.palevobot.entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserNoSqlDaoTest {
    NoSqlDao noSqlDao;
    User user;
    @Before
    public void setUp() throws Exception {
        noSqlDao = (NoSqlDao) new UserDaoFactory().getDao("nosql");
        user = new User(0, "title");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insert() throws IOException, SQLException {
        noSqlDao.insert(user);
        User newUser =(User) noSqlDao.getById(user.getId());
        Assert.assertEquals(user.toString(), newUser.toString());
    }

    @Test
    public void getAll() throws SQLException {
        for (User user : (ArrayList<User>)noSqlDao.getAll()) {
            System.out.println(user.getUsername());
        }
    }
}