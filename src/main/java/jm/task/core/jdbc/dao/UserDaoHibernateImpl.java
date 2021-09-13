package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class UserDaoHibernateImpl implements UserDao {

    Session session = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        session = UserServiceImpl.getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("CREATE TABLE `mydbtest`.`UserDao` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`id`))").addEntity(User.class);
        query.executeUpdate();
    }

    @Override
    public void dropUsersTable() {
        session = UserServiceImpl.getSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("drop  table `mydbtest`.`UserDao`").addEntity(User.class);
        query.executeUpdate();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = UserServiceImpl.getSession();
        session.beginTransaction();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        // commit() - следовательно сессию закрыли
        System.out.println("Пользователь " + name + " Добавлен в таблицу");
    }

    @Override
    public void removeUserById(long id) {
        session = UserServiceImpl.getSession();
        session.beginTransaction();
        User userFromDB = (User) session.get(User.class, id);
        session.delete(userFromDB);
    }

    @Override
    public List<User> getAllUsers() {
        session = UserServiceImpl.getSession();
        session.beginTransaction();
        List<User> userList = session.createCriteria(User.class).list();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        session = UserServiceImpl.getSession();
        session.beginTransaction();
        session.createSQLQuery("delete from userdao").addEntity(User.class).executeUpdate();
    }

}
