package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost/test";
    private static final String USER = "testuser";
    private static final String PASSWORD = "test";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void close(Connection con) throws SQLException {
        con.close();
    }

    public void commit(Connection con) throws SQLException {
        con.commit();
    }

    public void rollback(Connection con) throws SQLException {
        con.rollback();
    }
}
