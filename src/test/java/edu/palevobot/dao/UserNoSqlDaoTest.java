package edu.palevobot.dao;

import edu.palevobot.dao.user.DaoType;
import edu.palevobot.dao.user.UserDaoFactory;
import edu.palevobot.dao.user.UserNoSqlDao;
import edu.palevobot.entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserNoSqlDaoTest {

    UserNoSqlDao dao;
    User user;

    @Before
    public void setUp() throws Exception {
        Logger.getLogger( "org.mongodb.driver" ).setLevel(Level.SEVERE);
        dao = (UserNoSqlDao) new UserDaoFactory().getDao(DaoType.NoSQL);
        user = new User(0, "TEST ME");
    }

    @After
    public void tearDown() throws Exception {
        dao.drop();
        dao.closeConnection();
        user = null;
    }

    @Test
    public void insert() throws IOException, SQLException {
        Assert.assertEquals(0, dao.getAll().size());
        dao.insert(user);
        Assert.assertEquals(user, dao.getById(user.getId()));
        Assert.assertTrue(dao.getAll().contains(user));
        Assert.assertEquals(1, dao.getAll().size());
    }

    @Test
    public void getAll() throws SQLException, IOException {
        Assert.assertEquals(0, dao.getAll().size());

        User secondUser = new User(33, "SECOND");
        dao.insert(user);

        Assert.assertEquals(1, dao.getAll().size());

        dao.insert(secondUser);
        Assert.assertEquals(2, dao.getAll().size());
    }

    @Test
    public void update() throws SQLException, IOException {
        dao.insert(user);
        String newName = "UPDATED NAME";
        user.setUsername(newName);
        dao.update(user);

        Assert.assertEquals(user, dao.getById(user.getId()));
    }

    @Test
    public void delete() throws SQLException, IOException {
        dao.insert(user);
        dao.delete(user);
        Assert.assertEquals(0, dao.getAll().size());
        Assert.assertNull(dao.getById(user.getId()));
        Assert.assertFalse(dao.getAll().contains(user));
    }

    @Test
    public void drop() throws SQLException, IOException {
        dao.insert(user);
        dao.insert(new User(99, "SECOND"));
        dao.drop();
        Assert.assertEquals(0, dao.getAll().size());
    }

    @Test
    public void getById() throws SQLException, IOException {
        dao.insert(user);
        User result = dao.getById(user.getId());
        Assert.assertEquals(result.getUsername(), user.getUsername());
        Assert.assertEquals(result.getId(), user.getId());
    }

    @Test
    public void getByUserName() throws SQLException, IOException {
        dao.insert(user);
        Assert.assertNotNull(dao.getByUserName(user.getUsername()));
    }
}