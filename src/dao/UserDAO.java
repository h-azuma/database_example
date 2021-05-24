package dao;

import value.UserValue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends CommonDAO {

    public UserDAO() {
        super();
    }

    public List<UserValue> findAll() { // UserTableの全Userを取得する
        List<UserValue> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";


        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserValue user = new UserValue();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public UserValue findById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        UserValue user = null;

        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void insertUser(UserValue user) { // Userテーブルに1つのレコードを挿入する
        String sql = "INSERT INTO user VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAddress());

            ps.executeUpdate();

            System.out.println("Inserted " + user.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(UserValue user) {
        String sql = "DELETE FROM user WHERE id = ?";

        try (PreparedStatement pstmt = getCon().prepareStatement(sql)) {
            pstmt.setString(1, user.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
