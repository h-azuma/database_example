package dao;

import database.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class CommonDAO {
    private Connection con;

    public CommonDAO() {
        ConnectionManager connectionManager = new ConnectionManager();
        try {
            con = connectionManager.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getCon() {
        return con;
    }
}
