package jm.task.core.jdbc;


import com.google.protobuf.Internal;
import com.mysql.cj.jdbc.CallableStatement;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDaoHibernateImpl udhi = new UserDaoHibernateImpl();
        udhi.dropUsersTable();
        udhi.createUsersTable();
        udhi.saveUser("Sophia", "Lubinia", (byte)21);
        udhi.saveUser("Shamil", "Shundalov", (byte)18);
        udhi.saveUser("Mikhail", "Lazarev", (byte)15);
        udhi.cleanUsersTable();
    }
}