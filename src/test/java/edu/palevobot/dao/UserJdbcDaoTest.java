package edu.palevobot.dao;

import edu.palevobot.dao.user.DaoType;
import edu.palevobot.dao.user.UserDaoFactory;
import edu.palevobot.dao.user.UserJdbcDao;
import edu.palevobot.entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class UserJdbcDaoTest {

    UserJdbcDao dao;
    User user;

    @Before
    public void setUp() throws Exception {
        dao =  (UserJdbcDao) new UserDaoFactory().getDao(DaoType.SQL);
        user = new User(23, "TEST ME");
        //TestCommit
    }

    @After
    public void tearDown() throws Exception {
        dao.truncate();
        dao.closeConnection();
        user = null;
    }

    @Test
    public void insert() throws SQLException, IOException {
        dao.insert(user);
        Assert.assertEquals(user, dao.getByUsername((user.getUsername())));
    }

    @Test
    public void delete() throws SQLException {
        dao.insert(user);
        dao.delete(user);
        Assert.assertNull(dao.getById(user.getId()));
    }

    @Test
    public void truncate() throws SQLException{
        User secondUser = new User(11, "SECOND");
        dao.insert(user);
        dao.insert(secondUser);
        Assert.assertEquals(2, dao.getAll().size());

        dao.truncate();
        Assert.assertEquals(0, dao.getAll().size());
    }

    @Test
    public void getAll() throws SQLException{
        User secondUser = new User(6, "SECOND USER");

        dao.insert(user);
        Assert.assertEquals(1, dao.getAll().size());

        dao.insert(secondUser);
        Assert.assertEquals(2, dao.getAll().size());
    }

    @Test
    public void getByUsername() throws SQLException{
        dao.insert(user);

        Assert.assertEquals(user, dao.getByUsername(user.getUsername()));
    }

    @Test
    public void getById() throws SQLException{
        dao.insert(user);
        Assert.assertEquals(user, dao.getById(user.getId()));
    }

    @Test
    public void update() throws SQLException{
        //i think that it is failed because getById() works wrong and it can not find user with id
        dao.insert(user);
        String newName = "UPDATED NAME";
        user.setUsername(newName);
        dao.update(user);
        Assert.assertEquals(user, dao.getByUsername(newName));
    }
}