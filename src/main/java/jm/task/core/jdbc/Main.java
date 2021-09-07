package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Shamil", "Shundalov", (byte)18);
        userService.saveUser("Misha", "Shunddalov", (byte)11);
        userService.saveUser("Liza", "Chernikova", (byte) 21);
        userService.saveUser("Alex", "Kavani", (byte) 24);
        System.out.println(userService.getAllUsers().get(0));
    }
}
