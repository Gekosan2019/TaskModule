package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        userDao.createUsersTable();
        userDao.saveUser("Shamil", "Shundalov", (byte)18);
        userDao.saveUser("Misha", "Shunddalov", (byte)11);
        System.out.println(userDao.getAllUsers().get(0));
       // userDao.saveUser("Shamil", "Shundalov", (byte) 18);
       // userDao.saveUser("Liza", "Chernikova", (byte) 21);
       // userDao.saveUser("John", "Travolta", (byte) 19);
       // userDao.saveUser("Alex", "Kavani", (byte) 24);
    }
}
