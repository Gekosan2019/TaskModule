package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoHibernateImpl();
    private SessionFactory sessionFactory = Util.getSessionFactory();
    private static Session session = null;


    public void createUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            userDao.createUsersTable();
        } catch (Exception e) {
            System.out.println("Таблица уже создана");
        } finally {
            session.getTransaction().commit();
        }
    }

    public void dropUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            userDao.dropUsersTable();
        } catch (Exception e) {
            System.out.println("Таблицы не существует");
        } finally {
            session.getTransaction().commit();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            session = sessionFactory.getCurrentSession();
            userDao.saveUser(name, lastName, age);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.getTransaction().commit();
        }
    }

    public void removeUserById(long id) throws SQLException {
        try {
            session = sessionFactory.getCurrentSession();
            userDao.removeUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        try {
            session = sessionFactory.getCurrentSession();
            return userDao.getAllUsers();
        } finally {
            session.getTransaction().commit();
        }
    }

    public void cleanUsersTable() throws SQLException {
        try {
            session = sessionFactory.getCurrentSession();
            userDao.cleanUsersTable();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
    }

    public static Session getSession() {
        return session;
    }

}


