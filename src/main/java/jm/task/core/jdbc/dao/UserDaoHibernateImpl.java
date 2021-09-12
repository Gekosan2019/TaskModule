package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class UserDaoHibernateImpl implements UserDao {

        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE `mydbtest`.`UserDao` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastname` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT(3) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))").addEntity(User.class);
            query.executeUpdate();
        } catch (NullPointerException e) {
            System.out.println("Проверьте подключение к БД");
        } catch (Exception e) {
            System.out.println("Такая таблица уже создана");
        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("drop  table `mydbtest`.`UserDao`").addEntity(User.class);
            query.executeUpdate();
        } catch (NullPointerException e) {
            System.out.println("Проверьте подключение к БД");
        } catch(Exception e) {
            System.out.println("Такой таблицы не существует");
        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = sessionFactory.getCurrentSession();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.beginTransaction();
            session.save(user);
            // commit() - следовательно сессию закрыли
            System.out.println("Пользователь " + name + " Добавлен в таблицу");
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("Не удалось установить соединение с БД");
        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User userFromDB = (User) session.get(User.class, id);
            session.delete(userFromDB);
        } catch (NullPointerException exception) {
            System.out.println("Проверьте подключение к БД");
        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try{
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            userList = session.createCriteria(User.class).list();
        } catch (NullPointerException throwables) {
            System.out.println("Проверьте подключение к БД");
        } finally {
            session.getTransaction().commit();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try{
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<User> userList = session.createCriteria(User.class).list();
            ListIterator<User> listIterator = userList.listIterator();
            while(listIterator.hasNext()){
                session.delete(userList.get(listIterator.nextIndex()));
                listIterator.next();
            }
        } catch (NullPointerException exception) {
            System.out.println("Проверьте подключение к БД");
        } finally {
            session.getTransaction().commit();
        }
    }
}
