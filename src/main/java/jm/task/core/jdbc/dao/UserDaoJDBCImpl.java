package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.exception.SQLGrammarException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private PreparedStatement preparedStatement;
    private final String INSERT_NEW = "insert into userdao values (null, ?, ?, ?)";

    public UserDaoJDBCImpl() {

    }

    Util util = new Util();

    public void createUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE `mydbtest`.`UserDao` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastname` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT(3) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))");
        } catch (SQLSyntaxErrorException throwables) {
            System.out.println("Таблица с таким именем уже существует");
        } catch (SQLException throwables) {
            System.out.println("Проверьте подключение к БД");
        }
    }

    public void dropUsersTable() {
        try(Statement statement  = util.getConnection().createStatement()) {
            statement.executeUpdate("drop  table `mydbtest`.`UserDao`");
        } catch (SQLSyntaxErrorException throwables) {
            System.out.println("Таблицы с таким именем не существует");
        } catch (SQLException throwables) {
            System.out.println("Проверьте подключение к БД");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = util.getConnection().createStatement()) {
            String query = "insert into userdao (id, name, lastname, age) values (null, ?, ?, ?);";
            preparedStatement = statement.getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate("delete from userdao where id = " + id);
        } catch (SQLException throwables) {
            System.out.println("Проверьте подключение к БД");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(Statement statement = util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from userdao;");
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            System.out.println("Проверьте подключение к БД");
        }
        if (userList.isEmpty()) {
            return userList;
        } else {
            return userList;
        }
    }

    public void cleanUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate("delete from userdao");
        } catch (SQLSyntaxErrorException throwables) {
            System.out.println("Таблицы с таким именем не существует");
        } catch (SQLException throwables) {
            System.out.println("Проверьте подключение к БД");
        }
    }
}
